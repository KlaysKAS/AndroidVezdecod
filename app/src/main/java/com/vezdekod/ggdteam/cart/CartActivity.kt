package com.vezdekod.ggdteam.cart

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vezdekod.ggdteam.App
import com.vezdekod.ggdteam.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity(), UpdateCostInt {
    private lateinit var binding: ActivityCartBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cartRecycler = binding.cartRecycler
        cartRecycler.layoutManager = LinearLayoutManager(this)
        cartRecycler.adapter = CartRecyclerAdapter(up = this)
        update()
        binding.cartToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun update() {
        if (App.cart.itemCount == 0) {
            binding.emptyCart.visibility = View.VISIBLE
            binding.cartButton.visibility = View.GONE
        }
        else {
            binding.emptyCart.visibility = View.GONE
            binding.cartButton.visibility = View.VISIBLE
        }
        binding.cartButton.text = "Заказать за ${App.cart.getTotalCost() / 100.0} ₽"
    }
}

interface UpdateCostInt {
    fun update()
}