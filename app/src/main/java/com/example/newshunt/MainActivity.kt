package com.example.newshunt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newshunt.news_details.NewsArticleScreen
import com.example.newshunt.ui.theme.NewsHuntTheme
import com.example.newshunt.utils.Route
import com.example.newshunt.viewmodel.NewsViewModel
import com.kwabenaberko.newsapilib.models.Article

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        val newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
//        setContent {
//            NewsHuntTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Column(
//                        modifier = Modifier.padding(innerPadding)
//                            .fillMaxSize()
//                    ){
//                        Text(text = "TOP NEWS",
//                            modifier = Modifier.align(Alignment.CenterHorizontally),
//                            color = Color.Blue,
//                            fontSize = 24.sp,
//                            fontFamily = FontFamily.SansSerif
//                            )
//                        val navController = rememberNavController()
////                        NavHost(navController = navController,startDestination = "/home"){
////                            composable("/home") {
////                                HomeScreen(newsViewModel,navController)
////                            }
////                            composable("/news article/article={Article}"){
////                                val newsJson = it.arguments?.getString("Article")
////                                val article = Route.newsDetailsRoute(newsJson!!)
////
////
////
////
////                                NewsArticleScreen(navController = navController,article)
////                            }
//
//
//                        }
//                    }
//                }
//            }
//        }
//    }
//    @Composable
//    fun NewsAppNavHost(navController: NavHostController,
//                       newsViewModel: NewsViewModel){
//        NavHost(navController = navController, startDestination = "/home"){
//            composable("/home"){
//                HomeScreen(newsViewModel,navController)
//            }
//            composable(
//                "/news article/image={image}&url={url}&title={title}&author={author}&description={description}&publishedAt={publishedAt}&content={content}",
//                arguments = listOf(
//                    navArgument("image"){
//                        type = androidx.navigation.NavType.StringType
//                    },
//                    navArgument("url"){
//                        type = androidx.navigation.NavType.StringType
//                    },
//                    navArgument("title") { type = androidx.navigation.NavType.StringType },
//                    navArgument("author") { type = androidx.navigation.NavType.StringType },
//                    navArgument("description") { type = androidx.navigation.NavType.StringType },
//                    navArgument("publishedAt") { type = androidx.navigation.NavType.StringType },
//                    navArgument("content") { type = androidx.navigation.NavType.StringType }
//
//
//                )
//
//            )
//        }
//
//}
//



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        setContent {
            NewsHuntTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "TOP NEWS",
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            color = Color.Blue,
                            fontSize = 24.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                        val navController = rememberNavController()
                        NewsAppNavHost(navController = navController, newsViewModel = newsViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun NewsAppNavHost(navController: NavHostController, newsViewModel: NewsViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(newsViewModel, navController)
        }
        composable(
            "news_details/image={image}&url={url}&title={title}&author={author}&description={description}&publishedAt={publishedAt}&content={content}",
            arguments = listOf(
                navArgument("image") { type = androidx.navigation.NavType.StringType },
                navArgument("url") { type = androidx.navigation.NavType.StringType },
                navArgument("title") { type = androidx.navigation.NavType.StringType },
                navArgument("author") { type = androidx.navigation.NavType.StringType },
                navArgument("description") { type = androidx.navigation.NavType.StringType },
                navArgument("publishedAt") { type = androidx.navigation.NavType.StringType },
                navArgument("content") { type = androidx.navigation.NavType.StringType }
            )
        ) {
            val image = it.arguments?.getString("image")
            val url = it.arguments?.getString("url")
            val title = it.arguments?.getString("title")
            val author = it.arguments?.getString("author")
            val description = it.arguments?.getString("description")
            val publishedAt = it.arguments?.getString("publishedAt")
            val content = it.arguments?.getString("content")

            NewsArticleScreen(
                navController = navController,
                article = Article().apply {
                    this.urlToImage = image
                    this.url = url
                    this.author = author
                    this.title = title
                    this.description = description
                    this.publishedAt = publishedAt
                    this.content = content
                }
            )
        }
    }
}
