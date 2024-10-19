package com.example.newspod.presentation.newsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newspod.data.database.NewsDatabase
import com.example.newspod.data.model.News
import com.example.newspod.data.response.NewsResponse
import com.example.newspod.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
enum class BookmarkAction {
    ADD, REMOVE
}
@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    database: NewsDatabase
):ViewModel() {
    private val _state = MutableStateFlow<State<BookmarkAction>>(State.Loading)
    val state = _state as StateFlow<State<BookmarkAction>>
    private val newsDao = database.newsDao()

    fun addNews(news: News){
        viewModelScope.launch {
            try {
                _state.tryEmit(State.Loading)
                newsDao.addNews(news)
                _state.tryEmit(State.Success(BookmarkAction.ADD))
            }catch (e:Exception){
                _state.tryEmit(State.Error(e.message.toString()))

            }
        }
    }

    fun removeNews(news: News) {
        viewModelScope.launch {
            try {
                _state.tryEmit(State.Loading)
                newsDao.deleteNews(news)
                _state.tryEmit(State.Success(BookmarkAction.REMOVE))
            } catch (e: Exception) {
                _state.tryEmit(State.Error(e.message.toString()))
            }
        }
    }

}