package com.vezdekod.ggdteam.cart

import android.annotation.SuppressLint
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vezdekod.ggdteam.App
import com.vezdekod.ggdteam.R
import com.vezdekod.ggdteam.menu.MenuItem

class CartRecyclerAdapter(private val menuItem: MutableList<MenuItem> = App.cart.listItem.keys.toMutableList(), val up: UpdateCostInt):
    RecyclerView.Adapter<CartRecyclerAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivImage: ImageView = itemView.findViewById(R.id.cart_item_image)
        private val ivName: TextView = itemView.findViewById(R.id.cart_item_name)
        private val ivMinus: ImageButton = itemView.findViewById(R.id.cart_item_minus)
        private val ivPlus: ImageButton = itemView.findViewById(R.id.cart_item_plus)
        private val ivCounter: TextView = itemView.findViewById(R.id.cart_item_counter)
        private val ivNewCost: TextView = itemView.findViewById(R.id.cart_item_new_price)
        private val ivOldCost: TextView = itemView.findViewById(R.id.cart_item_old_price)

        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun bind(item: MenuItem) {
            ivImage.setImageResource(R.drawable.food_example)
            ivName.text = item.name
            ivNewCost.text = "${item.priceCurrent / 100.0} ₽"
            if (item.priceOld != null) {
                ivOldCost.text = "${item.priceOld / 100.0} ₽"
                ivOldCost.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                ivOldCost.visibility = View.VISIBLE
            } else {
                ivOldCost.visibility = View.GONE
            }
            ivCounter.text = App.cart.getItemCount(item).toString()

            ivPlus.setOnClickListener {
                App.cart.addItem(item)
                ivCounter.text = App.cart.getItemCount(item).toString()
                up.update()
            }

            ivMinus.setOnClickListener {
                App.cart.deleteItem(item)
                ivCounter.text = App.cart.getItemCount(item).toString()
                if (ivCounter.text == "0") {
                    menuItem.remove(item)
                    notifyDataSetChanged()
                }
                up.update()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_cart_item, parent, false)
        return CartViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(menuItem[position])
    }

    override fun getItemCount(): Int = menuItem.size
}