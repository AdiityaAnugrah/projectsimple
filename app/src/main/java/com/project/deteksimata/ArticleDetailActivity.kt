package com.project.deteksimata

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ArticleDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        val titleTextView: TextView = findViewById(R.id.articleTitle)
        val contentTextView: TextView = findViewById(R.id.articleContent)

        // Mengambil data dari Intent
        val title = intent.getStringExtra("ARTICLE_TITLE")
        val content = intent.getStringExtra("ARTICLE_CONTENT")

        // Menampilkan data pada TextView
        titleTextView.text = title
        contentTextView.text = content
    }
}
