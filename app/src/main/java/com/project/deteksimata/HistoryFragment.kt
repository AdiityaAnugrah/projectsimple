package com.project.deteksimata


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.deteksimata.sharedPreference.HistoryPreference

class HistoryFragment : Fragment() {
    private lateinit var historyPreference: HistoryPreference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.historyRecyclerView)
        historyPreference = HistoryPreference(requireContext())
        val data = historyPreference.getHistory("historyPref")

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = HistoryAdapter(data)

        return view
    }
}
