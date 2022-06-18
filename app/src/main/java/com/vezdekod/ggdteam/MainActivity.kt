package com.vezdekod.ggdteam

import android.graphics.Color
import android.graphics.ColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.vezdekod.ggdteam.categories.CategoryRecyclerAdapter
import com.vezdekod.ggdteam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categoryRecycler = binding.mainCategoryRecycler
        categoryRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        categoryRecycler.adapter = CategoryRecyclerAdapter(
            listOf(
                "Роллы",
                "Пицца",
                "Суши",
                "Горячие блюда"
            )
        )
    }
}