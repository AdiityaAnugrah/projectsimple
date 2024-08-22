package com.project.deteksimata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        // Dummy data for ViewPager2
        val cards = listOf(
            CardItem(R.drawable.ic_eye, "Eye Disease 1"),
            CardItem(R.drawable.ic_eye, "Eye Disease 2"),
            CardItem(R.drawable.ic_eye, "Eye Disease 3")
        )
        val viewPagerAdapter = ViewPagerAdapter(cards)
        viewPager.adapter = viewPagerAdapter

        // Dummy data for RecyclerView
        val articles = listOf(
            Article("Article 1", "Content of Article 1"),
            Article("Article 2", "Content of Article 2"),
            Article("Article 3", "Content of Article 3")
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ArticleAdapter(articles)

        return view
    }
}
