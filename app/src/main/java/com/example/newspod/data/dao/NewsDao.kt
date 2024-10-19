package com.example.newspod.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.newspod.data.model.News
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {
    @Query("SELECT * FROM news")
    fun getNews():Flow<List<News>>

    @Insert
    suspend fun addNews(news: News)

    @Delete
    suspend fun deleteNews(news: News)
}