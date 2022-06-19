package com.vezdekod.ggdteam.menu

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vezdekod.ggdteam.App
import com.vezdekod.ggdteam.DetailsActivity
import com.vezdekod.ggdteam.R

class MenuRecyclerAdapter(private val menuItem: List<MenuItem>):
    RecyclerView.Adapter<MenuRecyclerAdapter.MenuViewHolder>() {

    @SuppressLint("ResourceAsColor")
    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val defaultCardViewElevation = 5.5f
        private val ivImage: ImageView = itemView.findViewById(R.id.menu_item_card_photo)
        private val ivSpicy: ImageView = itemView.findViewById(R.id.menu_item_card_specials_spicy)
        private val ivVegetarian: ImageView = itemView.findViewById(R.id.menu_item_card_specials_vegetarian)
        private val ivSale: ImageView = itemView.findViewById(R.id.menu_item_card_specials_sale)
        private val ivName: TextView = itemView.findViewById(R.id.menu_item_card_name)
        private val ivWeight: TextView = itemView.findViewById(R.id.menu_item_card_weight)
        private val ivNewCost: TextView = itemView.findViewById(R.id.menu_item_card_new_cost_text)
        private val ivOldCost: TextView = itemView.findViewById(R.id.menu_item_card_old_cost_text)
        private val ivButtonCost: LinearLayout = itemView.findViewById(R.id.menu_item_card_cost_card)
        private val ivButMinus: ImageButton = itemView.findViewById(R.id.menu_item_minus)
        private val ivButPlus: ImageButton = itemView.findViewById(R.id.menu_item_plus)
        private val ivCount: TextView = itemView.findViewById(R.id.menu_item_counter)
        private val ivBuyer: LinearLayout = itemView.findViewById(R.id.main_item_linear)
        private val commonCardView: CardView = itemView.findViewById(R.id.main_card_cost)

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

            val count = App.cart.getItemCount(item)
            ivCount.text = count.toString()
            if (count > 0) {
                ivButtonCost.isClickable = false
                ivButtonCost.visibility = View.VISIBLE
                ivBuyer.visibility = View.VISIBLE
                commonCardView.elevation = 0f
            } else {
                ivButtonCost.isClickable = true
                ivButtonCost.visibility = View.VISIBLE
                ivBuyer.visibility = View.GONE
            }

            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, DetailsActivity::class.java).putExtra("item", item)
                )
            }
            ivButtonCost.setOnClickListener {
                App.cart.addItem(item)
                ivButtonCost.isClickable = false
                ivButtonCost.visibility = View.INVISIBLE
                ivBuyer.visibility = View.VISIBLE
                ivCount.text = App.cart.getItemCount(item).toString()
                commonCardView.elevation = 0f
            }
            ivButMinus.setOnClickListener {
                App.cart.deleteItem(item)
                ivCount.text = App.cart.getItemCount(item).toString()
                if (ivCount.text == "0") {
                    ivButtonCost.isClickable = true
                    ivButtonCost.visibility = View.VISIBLE
                    ivBuyer.visibility = View.GONE
                    commonCardView.elevation = defaultCardViewElevation
                }
            }
            ivButPlus.setOnClickListener {
                App.cart.addItem(item)
                ivCount.text = App.cart.getItemCount(item).toString()
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