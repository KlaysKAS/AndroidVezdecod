package com.vezdekod.ggdteam.categories

import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken


class Category(
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("name")
    @Expose
    val name: String = ""
) {
    companion object {
        fun getListFromJSON(json: String?): List<Category>? {
            val builder = GsonBuilder()
            val gson = builder.create()
            val listType = object : TypeToken<List<Category>?>() {}.type
            return gson.fromJson(json, listType)
        }
    }
}



