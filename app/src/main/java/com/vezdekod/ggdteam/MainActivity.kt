package com.vezdekod.ggdteam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import com.vezdekod.ggdteam.cart.CartActivity
import com.vezdekod.ggdteam.cart.UpdateCostInt
import com.vezdekod.ggdteam.databinding.ActivityMainBinding
import com.vezdekod.ggdteam.menu.MenuRecyclerAdapter
import java.util.*

class MainActivity : AppCompatActivity(), UpdateCostInt {
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tab = binding.mainTabs
        App.category.forEach {
            val newTab = tab.newTab().setText(it.name)
            tab.addTab(newTab)
        }

        val menuRecycler = binding.mainMenuRecycler
        menuRecycler.layoutManager = GridLayoutManager(this, 2)
        val adapter = MenuRecyclerAdapter(App.menu, this)
        menuRecycler.adapter = adapter

        if (App.cart.itemCount > 0) View.VISIBLE else View.INVISIBLE

        binding.mainCartCost.setOnClickListener {
            val intent = Intent(baseContext, CartActivity::class.java)
            startActivity(intent)
        }

        binding.cartButton.setOnClickListener {
            startActivity(Intent(baseContext, CartActivity::class.java))
        }
    }

    @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
    override fun onResume() {
        super.onResume()
        binding.mainMenuRecycler.adapter?.notifyDataSetChanged()
        binding.mainCartCost.visibility =
            if (App.cart.itemCount > 0) View.VISIBLE else View.GONE
        binding.mainCartCostText.text = "${App.cart.getTotalCost() / 100.0} ₽"
        binding.counterText.text = App.cart.itemCount.toString()
    }

    @SuppressLint("SetTextI18n")
    override fun update() {
        binding.mainCartCost.visibility =
            if (App.cart.itemCount > 0) View.VISIBLE else View.GONE
        binding.mainCartCostText.text = "${App.cart.getTotalCost() / 100.0} ₽"
        binding.counterText.text = App.cart.itemCount.toString()
    }
}