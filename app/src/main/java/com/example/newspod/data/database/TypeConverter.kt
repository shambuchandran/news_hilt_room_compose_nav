package com.example.newspod.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {

//    @TypeConverter
//    fun fromString(value:String):List<String>{
//        return value.split(",").map { it }
//    }
//
//    @TypeConverter
//    fun toList(list: List<String>):String{
//        return list.joinToString(",")
//    }

    @TypeConverter
    fun fromString(value:String):List<String>{
        val gson=Gson()
        val listType=object : TypeToken<List<String>>(){}.type
        return gson.fromJson(value,listType)
    }

    @TypeConverter
    fun toList(list: List<String>):String{
        val gson=Gson()
        return gson.toJson(list)
    }

}