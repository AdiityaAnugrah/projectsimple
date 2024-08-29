package com.project.deteksimata


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.deteksimata.room.HistoryDatabase
import com.project.deteksimata.room.HistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryFragment : Fragment() {
    private lateinit var historyRepository: HistoryRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.historyRecyclerView)
        val historyDatabase = HistoryDatabase.getInstance(requireContext())
        val historyDao = historyDatabase.historyDao()
        historyRepository = HistoryRepository(historyDao)

        displayHistory(recyclerView)
        return view
    }

    private fun displayHistory(recyclerView: RecyclerView) {
        lifecycleScope.launch(Dispatchers.IO) {
            val historyList = historyRepository.getAllHistory()
            withContext(Dispatchers.Main) {
                recyclerView.layoutManager = LinearLayoutManager(context)
                // Set up the adapter with the retrieved history data
                recyclerView.adapter = HistoryAdapter(historyList)
            }
        }
    }
}
