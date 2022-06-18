package com.vezdekod.ggdteam

import android.graphics.Color
import android.graphics.ColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.vezdekod.ggdteam.categories.CategoryRecyclerAdapter
import com.vezdekod.ggdteam.databinding.ActivityMainBinding
import com.vezdekod.ggdteam.menu.MenuItem
import com.vezdekod.ggdteam.menu.MenuRecyclerAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryRecycler = binding.mainCategoryRecycler
        categoryRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        App.category?.let {
            categoryRecycler.adapter = CategoryRecyclerAdapter(it)
        }
        val menuRecycler = binding.mainMenuRecycler
        menuRecycler.layoutManager = GridLayoutManager(this, 2)
        menuRecycler.adapter = MenuRecyclerAdapter(App.menu)


    }
}