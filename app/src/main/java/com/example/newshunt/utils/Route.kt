package com.example.newshunt.utils

import androidx.room.util.copy
import com.google.gson.Gson
import com.kwabenaberko.newsapilib.models.Article
import java.net.URLDecoder
import java.net.URLEncoder


object Route {
    fun navRoute(article: Article): String{
        val encodedImage = URLEncoder.encode(article.urlToImage,"utf-8")
        val encodedUrl = URLEncoder.encode(article.url,"utf-8")
//        val tempNews = Article()
//
//        tempNews.source = article.source
//        tempNews.author = article.author
//        tempNews.title = article.title
//        tempNews.description = article.description
//        tempNews.url = encodedUrl
//        tempNews.urlToImage = encodedImage
//        tempNews.publishedAt = article.publishedAt
//        tempNews.content = article.content
//
//        val gson = Gson().toJson(tempNews)
//        return "news_details/article=$gson"
//
        return "news_details/image=$encodedImage&url=$encodedUrl&title=${article.title}&author=${article.author}&description=${article.description}&publishedAt=${article.publishedAt}&content=${article.content}"


    }
//    fun newsDetailsRoute(json: String): Article{
//        val article = Gson().fromJson(json,Article::class.java)
//        val decodedImage = URLDecoder.decode(article.urlToImage,"utf-8")
//        val decodedUrl = URLDecoder.decode(article.url,"utf-8")
//        return article.copy(image = decodedImage, url = decodedUrl)
//
//    }
fun newsDetailsRoute(encodedImage: String, encodedUrl: String): Pair<String, String> {
    val decodedImage = URLDecoder.decode(encodedImage, "utf-8")
    val decodedUrl = URLDecoder.decode(encodedUrl, "utf-8")
    return Pair(decodedImage, decodedUrl)
}
}
