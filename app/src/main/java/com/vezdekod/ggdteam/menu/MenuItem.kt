package com.vezdekod.ggdteam.menu

import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

data class MenuItem(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("category_id")
    @Expose
    val categoryId: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("description")
    @Expose
    val description: String,
    @SerializedName("image")
    @Expose
    val imageName: String,
    @SerializedName("price_current")
    @Expose
    val priceCurrent: Int,
    @SerializedName("price_old")
    @Expose
    val priceOld: Int,
    @SerializedName("measure")
    @Expose
    val measure: Int,
    @SerializedName("measure_unit")
    @Expose
    val measure_unit: String,
    @SerializedName("energy_per_100_grams")
    @Expose
    val energy_per_100_grams: Double,
    @SerializedName("proteins_per_100_grams")
    @Expose
    val proteins_per_100_grams: Double,
    @SerializedName("fats_per_100_grams")
    @Expose
    val fats_per_100_grams: Double,
    @SerializedName("carbohydrates_per_100_grams")
    @Expose
    val carbohydrates_per_100_grams: Double,
    @SerializedName("tag_ids")
    @Expose
    val tagId: List<Int> = listOf()
) {
    companion object {
        fun getListFromJSON(json: String?): List<MenuItem>? {
            val builder = GsonBuilder()
            val gson = builder.create()
            val listType = object : TypeToken<List<MenuItem>?>() {}.type
            return gson.fromJson(json, listType)
        }
    }
}
