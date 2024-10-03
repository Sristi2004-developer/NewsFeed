package com.example.newshunt.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newshunt.utils.Constants
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

class NewsViewModel: ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>> = _articles

init{
    fetchTopNews()
}

    fun fetchTopNews(){
        val newsApiClient = NewsApiClient(Constants.API_KEY)
        val request = TopHeadlinesRequest.Builder().language("en").build()
         newsApiClient.getTopHeadlines(request, object : NewsApiClient.ArticlesResponseCallback {
             override fun onSuccess(response: ArticleResponse?) {
                 response?.articles?.let{
                     _articles.postValue(it)

                 }
             }

             override fun onFailure(throwable: Throwable?) {
                 Log.i("Api response failed", "error: ${throwable?.message}")

             }

         })


    }
    fun searchQuery(query : String){
        val newsApiClient = NewsApiClient(Constants.API_KEY)
        val request = EverythingRequest.Builder().language("en").q(query).build()
        newsApiClient.getEverything(request, object : NewsApiClient.ArticlesResponseCallback {
            override fun onSuccess(response: ArticleResponse?) {
                response?.articles?.let{
                    _articles.postValue(it)

                }
            }

            override fun onFailure(throwable: Throwable?) {
                Log.i("Api response failed", "error: ${throwable?.message}")

            }

        })


    }


}