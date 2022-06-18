package com.vezdekod.ggdteam.menu

data class MenuItem(
    val id: Int,
    val categoryId: Int,
    val name: String,
    val description: String,
    val imageName: String,
    val priceCurrent: Int,
    val priceOld: Int,
    val measure: Int,
    val measure_unit: String,
    val energy_per_100_grams: Double,
    val proteins_per_100_grams: Double,
    val fats_per_100_grams: Double,
    val carbohydrates_per_100_grams: Double,
    val tagId: List<Int> = listOf()
)
