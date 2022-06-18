package com.vezdekod.ggdteam.cart

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        binding.cartButton.text = "Заказать за ${App.cart.getTotalCost() / 100.0} ₽"
    }
}

interface UpdateCostInt {
    abstract fun update()
}