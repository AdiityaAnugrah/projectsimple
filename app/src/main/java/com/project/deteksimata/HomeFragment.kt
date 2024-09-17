package com.project.deteksimata

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import articleList
import com.project.deteksimata.model.articleListViewPager

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)


        val viewPagerAdapter = ViewPagerAdapter(articleListViewPager) { cardItem ->
            // Mulai ListArticleActivity ketika kartu diklik
            val intent = Intent(context, ListArticleActivity::class.java)
            intent.putExtra("CATEGORY", cardItem.title)
            startActivity(intent)
        }
        viewPager.adapter = viewPagerAdapter
        val itemDecoration = HorizontalMarginItemDecoration(10)
        viewPager.addItemDecoration(itemDecoration)

        val articleAdapter = ArticleAdapter(articleList) { article ->
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
