package com.vezdekod.ggdteam

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.vezdekod.ggdteam.databinding.ActivityDetailsBinding
import com.vezdekod.ggdteam.menu.MenuItem

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        val item: MenuItem = intent.getSerializableExtra("item") as MenuItem
        binding.name.text = item.name
        binding.description.text = item.description
        binding.weight.text = "${item.measure} ${item.measure_unit}"
        binding.energy.text = "${item.energy_per_100_grams} ккал"
        binding.proteins.text = "${item.proteins_per_100_grams} г"
        binding.fats.text = "${item.fats_per_100_grams} г"
        binding.carbohydrates.text = "${item.carbohydrates_per_100_grams} г"
        binding.addButton.text = "В корзину за ${item.priceCurrent / 100.0} ₽"

        checkNumOfProducts(item)

        binding.addButton.setOnClickListener {
            App.cart.addItem(item)
            checkNumOfProducts(item)
        }
        binding.productCounterMinus.setOnClickListener {
            App.cart.deleteItem(item)
            checkNumOfProducts(item)
        }
        binding.productCounterPlus.setOnClickListener {
            App.cart.addItem(item)
            checkNumOfProducts(item)
        }
    }
    private fun checkNumOfProducts(item: MenuItem) {
        if (App.cart.getItemCount(item) > 0) {
            binding.addButton.visibility = View.INVISIBLE
            binding.productCounter.visibility = View.VISIBLE
            binding.productCounterNumber.text = App.cart.getItemCount(item).toString()
        }
        else {
            binding.addButton.visibility = View.VISIBLE
            binding.productCounter.visibility = View.INVISIBLE
        }
    }
}