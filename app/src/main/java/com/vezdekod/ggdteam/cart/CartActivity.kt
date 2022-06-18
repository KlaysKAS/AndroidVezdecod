package com.vezdekod.ggdteam.cart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vezdekod.ggdteam.databinding.ActivityCartBinding
import com.vezdekod.ggdteam.databinding.ActivityMainBinding

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cartRecycler = binding.cartRecycler
        cartRecycler.layoutManager = LinearLayoutManager(this)
        cartRecycler.adapter = CartRecyclerAdapter()

    }
}