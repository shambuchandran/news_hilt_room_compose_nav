package com.example.newspod.data.repository

import com.example.newspod.data.response.NewsResponse
import com.example.newspod.data.web.NewsApiInterface
import com.example.newspod.domain.repository.NewsRepository
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val api:NewsApiInterface) :NewsRepository {
    override suspend fun getNews(language: String, text: String?, country: String?): Response<NewsResponse> {
        return api.getNews(language, text, country)
    }

}