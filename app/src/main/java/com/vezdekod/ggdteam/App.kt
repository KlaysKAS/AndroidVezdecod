package com.vezdekod.ggdteam

import android.app.Application
import com.vezdekod.ggdteam.attributes.Attribute
import com.vezdekod.ggdteam.categories.Category
import com.vezdekod.ggdteam.menu.MenuItem

class App: Application() {
    companion object {
        lateinit var INSTANCE: App
        var category: List<Category> = listOf()
        var attribute: List<Attribute> = listOf()
        var menu: List<MenuItem> = listOf()
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}
