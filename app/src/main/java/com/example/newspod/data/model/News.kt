package com.example.newspod.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.newspod.data.database.TypeConverter

@Entity(tableName = "news")
@TypeConverters(TypeConverter::class)
data class News(
    val author: String?,
    val authors: List<String>?,
    val category: String?,
    @PrimaryKey(autoGenerate = true) val id: Int? = null ,
    val image: String,
    val language: String,
    val publish_date: String,
    val sentiment: Double,
    val source_country: String,
    val summary: String?,
    val text: String,
    val title: String,
    val url: String,
    val video: String?
)