package com.msousa.tmdbredux.data.local.entities.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.msousa.tmdbredux.data.remote.responses.Movie

class DataConverter {

    @TypeConverter
    fun toMovieString(list: List<Movie>?) = list?.let { Gson().toJson(it) } ?: ""

    @TypeConverter
    fun toListMovie(value: String?) : List<Movie> {
        val list = object : TypeToken<List<Movie>>(){}.type
        return if (list == null) emptyList()
        else Gson().fromJson(value, list)
    }
}