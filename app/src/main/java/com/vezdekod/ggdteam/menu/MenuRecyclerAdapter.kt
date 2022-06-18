package com.vezdekod.ggdteam.menu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vezdekod.ggdteam.R

class MenuRecyclerAdapter(private val menuItem: List<MenuItem>): RecyclerView.Adapter<MenuRecyclerAdapter.MenuViewHolder>() {

    @SuppressLint("ResourceAsColor")
    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.menu_item_card_photo)
        private val ivSpecials: ImageView = itemView.findViewById(R.id.menu_item_card_specials)
        private val ivName: TextView = itemView.findViewById(R.id.menu_item_card_name)
        private val ivWeight: TextView = itemView.findViewById(R.id.menu_item_card_weight)
        private val ivNewCost: TextView = itemView.findViewById(R.id.menu_item_card_new_cost_text)
        private val ivOldCost: TextView = itemView.findViewById(R.id.menu_item_card_old_cost_text)

        @SuppressLint("SetTextI18n")
        fun bind(item: MenuItem) {
//            ivImage.setImageBitmap()
//            ivSpecials.setImageBitmap()
            ivName.text = item.name
            ivWeight.text = "${item.measure} ${item.measure_unit}"
            ivNewCost.text = "${item.priceCurrent} ₽"
            if (item.priceOld != null)
                ivOldCost.text =  "${item.priceOld} ₽"
            else
                ivOldCost.visibility = View.GONE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.menu_item_card, parent, false)
        return MenuViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuItem[position])
    }

    override fun getItemCount(): Int = menuItem.size

}