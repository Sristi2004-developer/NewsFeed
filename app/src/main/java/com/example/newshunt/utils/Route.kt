package com.example.newshunt.utils

import androidx.room.util.copy
import com.google.gson.Gson
import com.kwabenaberko.newsapilib.models.Article
import kotlinx.serialization.Serializable
import java.net.URLDecoder
import java.net.URLEncoder



@Serializable
object HomePageScreen

@Serializable
data class NewsArticleScreen(
    val url : String
)
