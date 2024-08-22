package com.project.deteksimata


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.historyRecyclerView)

        val sharedPref = requireContext().getSharedPreferences("DetectionHistory", Context.MODE_PRIVATE)
        val historySet = sharedPref.getStringSet("history", setOf()) ?: setOf()
        val historyList = historySet.toList()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = HistoryAdapter(historyList)

        return view
    }
}
