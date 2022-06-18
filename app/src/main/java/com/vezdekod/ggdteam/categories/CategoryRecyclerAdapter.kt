package com.vezdekod.ggdteam.categories

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vezdekod.ggdteam.R

class CategoryRecyclerAdapter(private val names: List<String>): RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>() {
    private var items = mutableListOf<View>()

    inner class CategoryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.category_card_text)

        init {
            itemView.setOnClickListener {
                items.forEach { views ->
                    views.isSelected = it == views
                }
            }
            itemView.isSelected = false
        }

        fun bind(name: String) {
            textView.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.category_card, parent, false)
        items.add(itemView)
        return CategoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(names[position])

    }

    override fun getItemCount(): Int = names.size
}