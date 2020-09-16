package com.app.youtubemusic.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

public class Converter {

    //list to string
    @TypeConverter
    fun fromContentDetailsList(countryLang: List<ContentDetails?>?): String? {
        val type = object : TypeToken<List<ContentDetails>>() {}.type
        return Gson().toJson(countryLang, type)
    }

    //string to list
    @TypeConverter
    fun toContentDetailsList(countryLangString: String?): List<ContentDetails>? {
        val type = object : TypeToken<List<ContentDetails>>() {}.type
        return Gson().fromJson<List<ContentDetails>>(countryLangString, type)
    }
}