package com.vezdekod.ggdteam.cart

class CartItem(
    val image: String = "food_example",
    val name: String,
    val count: Int = 1,
    val costNew: Int,
    val oldCost: Int? = null
)