package com.example.newshunt

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.newshunt.utils.Route
import com.example.newshunt.viewmodel.NewsViewModel
import com.kwabenaberko.newsapilib.models.Article


@Composable
fun HomeScreen (newsViewModel: NewsViewModel,navController: NavHostController) {

    val articles by newsViewModel.articles.observeAsState(emptyList())
    val searchText = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(text = searchText.value, onSearch = { searchText.value = it }, viewModel = newsViewModel)
        Spacer(modifier = Modifier.padding(10.dp))


        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(articles) { article ->
                NewsItem(article, onClick = {
                    navController.navigate(Route.navRoute(article))
                })


            }


        }
    }
}


@Composable
fun NewsItem(article: Article, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier.padding(10.dp)
        .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),

            ){
                AsyncImage(model = article.urlToImage?:R.drawable.baseline_no_photography_24,
                    contentDescription = "Image",
                    modifier = Modifier.fillMaxWidth()

                )
                Text(text = article.title,

                    fontWeight = FontWeight.Bold,
                    maxLines = 3
                    )
//                Text(text = article.source,
//                    maxLines = 1,
//                    fontSize = 14.sp)

            }

        }
}
@Composable
fun SearchBar(text: String, onSearch: (String) -> Unit,viewModel: NewsViewModel){
    Box(modifier = Modifier.fillMaxWidth()){
        OutlinedTextField(value = text,
            onValueChange = {onSearch(it)
                    viewModel.searchQuery(it)},
            label = { Text(text = "Search")},
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()

            )
        Image(
            painter = painterResource(id = R.drawable.baseline_search_24),
            contentDescription = "Search",
            modifier = Modifier
                .padding(end = 10.dp)
                .align(Alignment.CenterEnd)
        )

    }
}