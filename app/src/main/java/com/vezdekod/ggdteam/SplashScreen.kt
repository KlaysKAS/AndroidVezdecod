package com.vezdekod.ggdteam

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.vezdekod.ggdteam.attributes.Attribute
import com.vezdekod.ggdteam.categories.Category
import com.vezdekod.ggdteam.menu.MenuItem
import kotlinx.coroutines.*
import org.json.JSONObject
import java.nio.charset.Charset

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataLoadJob = GlobalScope.launch(Dispatchers.IO) {
            categoriesLoad()
            attributesLoad()
            menuLoad()
        }

        runBlocking {
            launch(Dispatchers.IO) {
                dataLoadJob.join()
            }
        }


        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun categoriesLoad() {
        val file = baseContext.assets.open("categories.json")
        val size = file.available()
        val buff = ByteArray(size)
        file.read(buff)
        file.close()
        val json = String(buff, Charset.forName("UTF-8"))
        Category.getListFromJSON(json)?.let { App.category = it }
    }

    private fun attributesLoad() {
        val file = baseContext.assets.open("attributes.json")
        val size = file.available()
        val buff = ByteArray(size)
        file.read(buff)
        file.close()
        val json = String(buff, Charset.forName("UTF-8"))
        Attribute.getListFromJSON(json)?.let { App.attribute = it }
    }

    private fun menuLoad() {
        val file = baseContext.assets.open("menu.json")
        val size = file.available()
        val buff = ByteArray(size)
        file.read(buff)
        file.close()
        val json = String(buff, Charset.forName("UTF-8"))
        MenuItem.getListFromJSON(json)?.let { App.menu = it }
    }
}