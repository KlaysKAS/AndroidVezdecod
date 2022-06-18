package com.vezdekod.ggdteam.cart

import com.vezdekod.ggdteam.menu.MenuItem

class Cart {
    var listItem = mutableMapOf<MenuItem, Int>()
    val itemCount
        get(): Int {
            var totalCount = 0
            listItem.forEach {
                totalCount += it.value
            }
            return totalCount
        }

    fun deleteItem(menuItem: MenuItem) {
        for (i in listItem) {
            if (i.key == menuItem) {
                i.setValue(i.value - 1)
                if (i.value == 0)
                    listItem.remove(menuItem)
                break
            }
        }
    }

    fun addItem(menuItem: MenuItem) {
        var isAdded = false
        for (i in listItem) {
            if (i.key == menuItem) {
                i.setValue(i.value + 1)
                isAdded = true
                break
            }
        }
        if (!isAdded) {
            listItem[menuItem] = 1
        }
    }

    fun getItemCount(menuItem: MenuItem): Int {
        return listItem[menuItem] ?: 0
    }

    fun getTotalCost(): Int {
        var sum = 0
        listItem.forEach {
             sum += it.value * it.key.priceCurrent
        }
        return sum
    }
}