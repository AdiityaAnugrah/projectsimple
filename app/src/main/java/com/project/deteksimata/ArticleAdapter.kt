package com.project.deteksimata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ArticleAdapter(
    private val articles: List<Article>,
    private val onItemClick: (Article) -> Unit // Listener untuk klik item
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.article_title)
        val content: TextView = view.findViewById(R.id.article_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.title.text = article.title
        holder.content.text = article.content

        // Menambahkan listener untuk item klik
        holder.itemView.setOnClickListener {
            onItemClick(article) // Panggil lambda function saat item diklik
        }
    }

    override fun getItemCount(): Int = articles.size
}

data class Article(val title: String, val content: String)
