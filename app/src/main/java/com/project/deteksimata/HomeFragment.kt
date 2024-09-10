package com.project.deteksimata

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginLeft
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)

        // Data dummy untuk ViewPager2
        val cards = listOf(
            CardItem(R.drawable.sample, "Eye Disease 1"),
            CardItem(R.drawable.sample, "Eye Disease 2"),
            CardItem(R.drawable.sample, "Eye Disease 3")
        )
        val viewPagerAdapter = ViewPagerAdapter(cards) { cardItem ->
            // Mulai ListArticleActivity ketika kartu diklik
            val intent = Intent(context, ListArticleActivity::class.java)
            intent.putExtra("CATEGORY", cardItem.title)
            startActivity(intent)
        }
        viewPager.adapter = viewPagerAdapter
        val itemDecoration = HorizontalMarginItemDecoration(10)
        viewPager.addItemDecoration(itemDecoration)

        // Data dummy untuk RecyclerView
        val articles = listOf(
            Article("Article 1", "Content of Article 1"),
            Article("Article 2", "Content of Article 2"),
            Article("Article 3", "Content of Article 3")
        )
        val articleAdapter = ArticleAdapter(articles) { article ->
            // Mulai ArticleDetailActivity ketika artikel diklik
            val intent = Intent(context, ArticleDetailActivity::class.java)
            intent.putExtra("ARTICLE_TITLE", article.title)
            intent.putExtra("ARTICLE_CONTENT", article.content)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = articleAdapter

        return view
    }
}


class HorizontalMarginItemDecoration(private val horizontalMarginInPx: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.right = horizontalMarginInPx
        outRect.left = horizontalMarginInPx
    }
}
