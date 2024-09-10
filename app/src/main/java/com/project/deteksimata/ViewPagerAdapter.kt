package com.project.deteksimata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(
    private val items: List<CardItem>,
    private val onCardClick: (CardItem) -> Unit // Listener untuk menangani klik pada item
) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imgViewPager)
        val title: TextView = view.findViewById(R.id.titleViewPager)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.title.text = item.title

        // Menambahkan listener klik pada item
        holder.itemView.setOnClickListener {
            onCardClick(item) // Panggil lambda function saat item diklik
        }
    }

    override fun getItemCount(): Int = items.size
}

data class CardItem(val imageResId: Int, val title: String)
