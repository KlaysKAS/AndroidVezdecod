package com.vezdekod.ggdteam.cart

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vezdekod.ggdteam.App
import com.vezdekod.ggdteam.databinding.ActivityCartBinding
import com.vezdekod.ggdteam.databinding.ActivityMainBinding

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
        if (App.cart.getTotalCost() > 0) {
            binding.cartButton.visibility = View.VISIBLE
            binding.emptyCartText.visibility = View.GONE
            binding.cartButton.text = "Заказать за ${App.cart.getTotalCost() / 100.0} ₽"
        }
        else {
            binding.cartButton.visibility = View.GONE
            binding.emptyCartText.visibility = View.VISIBLE
        }
    }
}

interface UpdateCostInt {
    abstract fun update()
}