package com.vezdekod.ggdteam.menu

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vezdekod.ggdteam.R

class MenuRecyclerAdapter(private val menuItem: List<MenuItem>) :
    RecyclerView.Adapter<MenuRecyclerAdapter.MenuViewHolder>() {

    @SuppressLint("ResourceAsColor")
    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.menu_item_card_photo)
        private val ivSpicy: ImageView = itemView.findViewById(R.id.menu_item_card_specials_spicy)
        private val ivVegetarian: ImageView = itemView.findViewById(R.id.menu_item_card_specials_vegetarian)
        private val ivSale: ImageView = itemView.findViewById(R.id.menu_item_card_specials_sale)
        private val ivName: TextView = itemView.findViewById(R.id.menu_item_card_name)
        private val ivWeight: TextView = itemView.findViewById(R.id.menu_item_card_weight)
        private val ivNewCost: TextView = itemView.findViewById(R.id.menu_item_card_new_cost_text)
        private val ivOldCost: TextView = itemView.findViewById(R.id.menu_item_card_old_cost_text)

        @SuppressLint("SetTextI18n")
        fun bind(item: MenuItem) {
            ivImage.setImageResource(R.drawable.food_example)
            if (item.tagId.contains(2)) ivVegetarian.visibility = View.VISIBLE else ivVegetarian.visibility = View.GONE
            if (item.tagId.contains(4)) ivSpicy.visibility = View.VISIBLE else ivSpicy.visibility = View.GONE
            if (item.priceOld != null) ivSale.visibility = View.VISIBLE else ivSale.visibility = View.GONE

            ivName.text = item.name
            ivWeight.text = "${item.measure} ${item.measure_unit}"
            ivNewCost.text = "${item.priceCurrent / 100.0} ₽"
            if (item.priceOld != null) {
                ivOldCost.text = "${item.priceOld / 100.0} ₽"
                ivOldCost.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                ivOldCost.visibility = View.VISIBLE
            } else {
                ivOldCost.visibility = View.GONE
            }
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