package com.example.newspod.domain.repository

import com.example.newspod.data.response.NewsResponse
import retrofit2.Response

interface NewsRepository {
    suspend fun getNews(language:String, text:String?, country:String?):Response<NewsResponse>

}