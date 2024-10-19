package com.example.newspod.data.web

import com.example.newspod.data.response.NewsResponse
import com.example.newspod.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApiInterface {
    @GET("search-news")
    suspend fun getNews(
        @Query("language") language:String,
        @Query("text") text:String?,
        @Query("country") country:String?,
        @Query("news-source") newsSource:String? = "https://www.bbc.co.uk",
        @Query("api-key") apiKey:String = API_KEY
    ):Response<NewsResponse>
}