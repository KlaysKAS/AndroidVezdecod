package com.vezdekod.ggdteam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import com.vezdekod.ggdteam.cart.CartActivity
import com.vezdekod.ggdteam.cart.CartItem
import com.vezdekod.ggdteam.databinding.ActivityMainBinding
import com.vezdekod.ggdteam.menu.MenuRecyclerAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tab = binding.mainTabs
        App.category.forEach {
            val newTab = tab.newTab().setText(it.name)
            tab.addTab(newTab)
        }

        val menuRecycler = binding.mainMenuRecycler
        menuRecycler.layoutManager = GridLayoutManager(this, 2)
        val adapter = MenuRecyclerAdapter(App.menu)
        menuRecycler.adapter = adapter

        binding.mainLogo.menu.get(0).setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_cart -> {
                    intent = Intent(baseContext, CartActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mainMenuRecycler.adapter?.notifyDataSetChanged()
    }
}