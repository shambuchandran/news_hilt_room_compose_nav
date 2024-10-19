package com.example.newspod.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspod.data.database.NewsDatabase
import com.example.newspod.data.model.News
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    database: NewsDatabase
) : ViewModel() {
    private val newsDao = database.newsDao()
    fun getBookmarks() = newsDao.getNews()
    fun deleteBookmark(news: News) {
        viewModelScope.launch {
            newsDao.deleteNews(news)
        }
    }

}