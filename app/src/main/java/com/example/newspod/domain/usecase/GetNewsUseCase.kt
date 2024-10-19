package com.example.newspod.domain.usecase

import com.example.newspod.data.response.NewsResponse
import com.example.newspod.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(
        language:String,
        text:String?,
        country:String?):NewsResponse {
        val response = newsRepository.getNews(language, text, country)
        if (response.body() == null){
            if (response.code() == 404)
                throw Exception("page or resource cannot be found")
            else if (response.code() == 500)
                throw Exception("Server Error")
            else if (response.code() == 401)
                throw Exception("Unauthorised")
            else if (response.code() == 400)
                throw Exception("Bad request")
            else
                throw Exception("No news found")
        }else{
            return response.body()!!
        }
    }

}