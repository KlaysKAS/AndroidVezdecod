package com.vezdekod.ggdteam

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
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
        menuRecycler.adapter = MenuRecyclerAdapter(App.menu)
    }
}