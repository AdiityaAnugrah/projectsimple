package com.project.deteksimata

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListArticleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_article)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Mengambil data kategori dari Intent
        val category = intent.getStringExtra("CATEGORY")

        // Dummy data artikel berdasarkan kategori
        val articles = listOf(
            Article("$category - Article 1", "Content of $category - Article 1"),
            Article("$category - Article 2", "Content of $category - Article 2"),
            Article("$category - Article 3", "Content of $category - Article 3")
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ArticleAdapter(articles) { article ->
            val intent = Intent(this, ArticleDetailActivity::class.java)
            intent.putExtra("ARTICLE_TITLE", article.title)
            intent.putExtra("ARTICLE_CONTENT", article.content)
            startActivity(intent)
        }
    }
}
