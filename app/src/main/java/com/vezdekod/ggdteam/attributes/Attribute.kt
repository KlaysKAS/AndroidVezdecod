package com.vezdekod.ggdteam.attributes

import com.google.gson.GsonBuilder
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

class Attribute(
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("name")
    @Expose
    val name: String = ""
) {
    companion object {
        fun getListFromJSON(json: String?): List<Attribute>? {
            val builder = GsonBuilder()
            val gson = builder.create()
            val listType = object : TypeToken<List<Attribute>?>() {}.type
            return gson.fromJson(json, listType)
        }
    }
}