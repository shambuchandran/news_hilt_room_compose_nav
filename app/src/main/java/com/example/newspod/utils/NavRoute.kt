package com.example.newspod.utils

import android.util.Base64
import com.example.newspod.data.model.News
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder


//object NavRoute {
//    fun createNewsDetailsRoute(news:News):String{
//        val encodedImage=URLEncoder.encode(news.image,"utf-8")
//        val encodedUrl=URLEncoder.encode(news.url,"utf-8")
//        val tempNews=news.copy(image = encodedImage, url = encodedUrl)
//        val gson=Gson().toJson(tempNews)
//        val encodedJson = Base64.encodeToString(gson.toByteArray(Charsets.UTF_8), Base64.NO_WRAP)
//        return "/details/news=$encodedJson"
//    }
//
//    fun getNewsFromRoute(encodedJson:String):News{
//        val json = String(Base64.decode(encodedJson, Base64.NO_WRAP), Charsets.UTF_8)
//        val news =Gson().fromJson(json,News::class.java)
//        val decodedImage=URLDecoder.decode(news.image,"utf-8")
//        val decodedUrl=URLDecoder.decode(news.url,"utf-8")
//        return news.copy(image = decodedImage, url = decodedUrl)
//    }
//}
//object NavRoute {
//    fun createNewsDetailsRoute(news: News, isLocal: Boolean? = false): String {
//        val encodedImage = URLEncoder.encode(news.image, "utf-8")
//        val encodedUrl = URLEncoder.encode(news.url, "utf-8")
//        val tempNews = news.copy(image = encodedImage, url = encodedUrl)
//        val gson = Gson().toJson(tempNews)
//        return "/details/news=$gson"
//    }
//
//    fun getNewsFromRoute(json: String): News {
//        val news = Gson().fromJson(json, News::class.java)
//        val decodedImage = URLDecoder.decode(news.image, "utf-8")
//        val decodeUrl = URLDecoder.decode(news.url, "utf-8")
//        return news.copy(image = decodedImage, url = decodeUrl)
//    }
//}
object NavRoute {
    fun createNewsDetailsRoute(news: News, isLocal: Boolean? = false): String {
        val gson = Gson().toJson(news)
        val encodedJson = URLEncoder.encode(gson, "utf-8")
        return "/details/news=$encodedJson&isLocal=$isLocal"
    }

    fun getNewsFromRoute(json: String): News {
        val decodedJson = URLDecoder.decode(json, "utf-8")
        return Gson().fromJson(decodedJson, News::class.java)
    }
}