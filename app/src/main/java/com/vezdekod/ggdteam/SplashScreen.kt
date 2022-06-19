package com.vezdekod.ggdteam

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieDrawable
import com.vezdekod.ggdteam.attributes.Attribute
import com.vezdekod.ggdteam.categories.Category
import com.vezdekod.ggdteam.databinding.SplashActivityBinding
import com.vezdekod.ggdteam.menu.MenuItem
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.nio.charset.Charset

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding: SplashActivityBinding
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animView = binding.animationView
        animView.setRepeatCount(1)
        animView.setRepeatMode(LottieDrawable.REVERSE)
        animView.setAnimation("anim/logo_anim.json")
        animView.playAnimation()

        animView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator?) {
                return
            }

            override fun onAnimationEnd(p0: Animator?) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationCancel(p0: Animator?) {
                return
            }

            override fun onAnimationRepeat(p0: Animator?) {
                return
            }
        })

        GlobalScope.launch(Dispatchers.IO) {
            categoriesLoad()
            attributesLoad()
            menuLoad()
        }


    }

    private fun categoriesLoad() {
        val file = baseContext.assets.open("categories.json")
        val size = file.available()
        val buff = ByteArray(size)
        file.read(buff)
        file.close()
        val json = String(buff, Charset.forName("UTF-8"))
        Category.getListFromJSON(json)?.let { App.category = it.sortedBy { it.id } }
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
        MenuItem.getListFromJSON(json)?.let { App.menu = it.sortedBy { it.categoryId } }
    }
}