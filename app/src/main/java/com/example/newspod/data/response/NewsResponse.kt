package com.example.newspod.data.response

import com.example.newspod.data.model.News

data class NewsResponse(
    val available: Int,
    val news: List<News>,
    val number: Int,
    val offset: Int
)