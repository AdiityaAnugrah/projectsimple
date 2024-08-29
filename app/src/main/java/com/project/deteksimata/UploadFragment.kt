package com.project.deteksimata

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.project.deteksimata.room.HistoryCacheEntity
import com.project.deteksimata.room.HistoryDatabase
import com.project.deteksimata.room.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel


class UploadFragment : Fragment() {

    private lateinit var tflite: Interpreter
    private lateinit var imageView: ImageView
    private lateinit var resultTextView: TextView
    private lateinit var historyRepository: HistoryRepository

    private val NUM_CLASSES = 4

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upload, container, false)

        val uploadButton: Button = view.findViewById(R.id.button_upload)
        imageView = view.findViewById(R.id.image_view)
        resultTextView = view.findViewById(R.id.result_text)
        val historyDatabase = HistoryDatabase.getInstance(requireContext())
        val historyDao = historyDatabase.historyDao()
        // Initialize the repository
        historyRepository = HistoryRepository(historyDao)

        try {
            tflite = Interpreter(loadModelFile())
        } catch (e: Exception) {
            e.printStackTrace()
            resultTextView.text = "Error initializing TensorFlow Lite model."
        }

        uploadButton.setOnClickListener {
            checkPermissionAndOpenGallery()
        }

        return view
    }

    private fun checkPermissionAndOpenGallery() {
        val readMediaPermission = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(requireContext(), readMediaPermission)
            == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(readMediaPermission),
                1
            )
        }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1)
    }
    @Deprecated("This method is deprecated. Use the new Activity Result APIs.")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        } else {
            resultTextView.text = "Permission denied."
        }
    }
    @Deprecated("This method is deprecated. Use the new Activity Result APIs.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == AppCompatActivity.RESULT_OK && data != null) {
            val uri = data.data
            if (uri != null) {
                val inputStream = requireContext().contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap)
                    val result = runModel(bitmap)
                    resultTextView.text = result

                    val fileName = uri.lastPathSegment?.split("/")?.last() ?: "Unknown File"
                    val historyItem = HistoryCacheEntity(filename = fileName, result = result)
                    saveHistory(historyItem)
                } else {
                    resultTextView.text = "Failed to load image."
                }
            } else {
                resultTextView.text = "No image selected."
            }
        } else {
            resultTextView.text = "Image upload canceled."
        }
    }

    private fun saveHistory(historyItem: HistoryCacheEntity) {
        // Use coroutine to perform database operation asynchronously
        lifecycleScope.launch(Dispatchers.IO) {
            historyRepository.saveHistory(historyItem)
        }
    }

    private fun loadModelFile(): MappedByteBuffer {
        val fileDescriptor = requireContext().assets.openFd("trained_model.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun runModel(bitmap: Bitmap): String {
        return try {
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, true)
            val input = Array(1) { Array(224) { Array(224) { FloatArray(3) } } }
            val output = Array(1) { FloatArray(NUM_CLASSES) }

            for (x in 0 until 224) {
                for (y in 0 until 224) {
                    val pixel = scaledBitmap.getPixel(x, y)
                    input[0][x][y][0] = (pixel shr 16 and 0xFF) / 255.0f
                    input[0][x][y][1] = (pixel shr 8 and 0xFF) / 255.0f
                    input[0][x][y][2] = (pixel and 0xFF) / 255.0f
                }
            }

            tflite.run(input, output)

            val maxIndex = output[0].indices.maxByOrNull { output[0][it] } ?: -1
            val diseaseLabels = listOf("Cataract", "Glaucoma", "Healthy", "Unknown")
            if (maxIndex != -1) {
                "Detected: ${diseaseLabels[maxIndex]} with confidence ${output[0][maxIndex]}"
            } else {
                "Could not detect disease."
            }
        } catch (e: Exception) {
            Log.e("ERRORTENSOR", e.message.toString())
            e.printStackTrace()
            "Error processing image: ${e.message}"
        }
    }
}