package com.example.newspod.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newspod.data.dao.NewsDao
import com.example.newspod.data.model.News

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class NewsDatabase:RoomDatabase() {
    abstract fun newsDao():NewsDao
    companion object{
        private const val DATABASE_NAME="news_db"

        fun getDatabase(context: Context):NewsDatabase{
            return Room.databaseBuilder(context,NewsDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }

    }
}