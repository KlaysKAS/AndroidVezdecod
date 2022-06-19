package com.vezdekod.ggdteam

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.tabs.TabLayout
import com.vezdekod.ggdteam.cart.CartActivity
import com.vezdekod.ggdteam.cart.UpdateCostInt
import com.vezdekod.ggdteam.databinding.ActivityMainBinding
import com.vezdekod.ggdteam.menu.MenuRecyclerAdapter

class MainActivity : AppCompatActivity(), UpdateCostInt, EmptySearchResult {
    private lateinit var binding: ActivityMainBinding

    private lateinit var gridLayoutManager: GridLayoutManager

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
        gridLayoutManager = GridLayoutManager(this, 2)
        menuRecycler.layoutManager = gridLayoutManager
        val adapter = MenuRecyclerAdapter(App.menu, this, this)
        menuRecycler.adapter = adapter

        binding.mainCartCost.setOnClickListener {
            val intent = Intent(baseContext, CartActivity::class.java)
            startActivity(intent)
        }

        binding.cartButton.setOnClickListener {
            startActivity(Intent(baseContext, CartActivity::class.java))
        }

        binding.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                adapter.filter.filter(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) = Unit
        })

        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    val catId = App.category[it.position].id
                    try {
                        val food = App.menu.first { menuItem -> menuItem.categoryId == catId }
//                        menuRecycler.scrollToPosition(App.menu.indexOf(food))
                        moveToPosition(App.menu.indexOf(food))
                    } catch (e: Exception) {
                        Toast.makeText(
                            applicationContext,
                            "Такой категории нет в наличии :(",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                return
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                return
            }
        })
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

    private fun moveToPosition(index: Int) {
        // Получаем позицию первого и последнего элемента, видимого на текущем экране recycleView
        val firstItem: Int = gridLayoutManager.findFirstVisibleItemPosition()
        val lastItem: Int = gridLayoutManager.findLastVisibleItemPosition()
        // Затем различаем ситуацию
        if (index <= firstItem) {
            // Когда элемент, который нужно покрыть, находится перед первым отображаемым элементом
            binding.mainMenuRecycler.scrollToPosition(index)
        } else if (index <= lastItem) {
            // Когда элемент, который нужно разместить сверху, уже отображается на экране, вычисляем расстояние от исходной точки экрана
            val top: Int = binding.mainMenuRecycler.getChildAt(index - firstItem).top
            binding.mainMenuRecycler.scrollBy(0, top)
        } else {
            // Когда элемент, который нужно покрыть, находится за последним элементом, отображаемым в данный момент
            binding.mainMenuRecycler.scrollToPosition(index)
            // Записываем текущую потребность продолжить вторую прокрутку в мониторе прокрутки RecyclerView
        }
    }

    override fun showEmptySearch(mode: Int) {
        binding.emptySearch.visibility = mode
    }
}

interface EmptySearchResult {
    fun showEmptySearch(mode: Int)
}