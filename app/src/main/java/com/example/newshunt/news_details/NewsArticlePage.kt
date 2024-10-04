package com.example.newshunt.news_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import  androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.kwabenaberko.newsapilib.models.Article


@Composable
fun NewsArticleScreen(navController: NavController,article: Article){

    NewsDetails(article= article)



}
@Composable
fun NewsDetails(article: Article){
    Box(modifier = Modifier.fillMaxSize()){
//        AsyncImage(model = article.image,
//            contentDescription = null,
//           modifier = Modifier
//               .fillMaxWidth()
//               .height(350.dp),
//            contentScale = ContentScale.Fit
//        )
        ConstraintLayout(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {

            val (imageRef, topSpace, details, newContent) = createRefs()

            AsyncImage(model = article.urlToImage, contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .constrainAs(imageRef) {
                        top.linkTo(parent.top)
                    },
                contentScale = ContentScale.Fit)

            Spacer(modifier = Modifier
                .height(30.dp)
                .constrainAs(topSpace) {
                    top.linkTo(imageRef.bottom)
                })


            Box(modifier = Modifier
                .constrainAs(newContent) {
                    top.linkTo(topSpace.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.wrapContent
                }
                .padding(14.dp)
                .background(Color.White)
            ) {
                Text(
                    text = article.description,
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxSize()
                )
            }


        }






    }

}
