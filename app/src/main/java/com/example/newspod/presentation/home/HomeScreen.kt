package com.example.newspod.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.newspod.data.model.News
import com.example.newspod.utils.NavRoute
import com.example.newspod.utils.State

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()
    val searchText = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        //search bar
        SearchBar(text = searchText.value, onSearch = {
            searchText.value = it
            viewModel.getNews(text = it)
        })
        Spacer(modifier = Modifier.height(16.dp))

        //category

        //news
        when (uiState.value) {
            is State.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Text(text = "Loading...")
                }
            }

            is State.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Failed to load")
                    Text(text = (uiState.value as State.Error).error)
                    Button(onClick = { viewModel.getNews(searchText.value) }) {
                        Text(text = "retry")
                    }
                }

            }

            else -> {
                val data = (uiState.value as State.Success).data
                NewsListView(news = data.news) {
                    navController.navigate(NavRoute.createNewsDetailsRoute(it))
                }
            }
        }
    }

}

@Composable
fun NewsListView(news: List<News>, onClick: (News) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(text = "News")
        }
        items(news) { article ->
            NewsItem(article, onClick = { onClick(article) })
        }
    }
}

@Composable
fun NewsItem(news: News, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(4.dp))
            .border(1.dp, color = Color.DarkGray)
            .background(Color.DarkGray.copy(0.2f))
            .clickable { onClick.invoke() }


    ) {
        AsyncImage(
            model = news.image,
            contentDescription = "Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
        Text(
            text = news.title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(4.dp)
        )
        Text(
            text = news.publish_date, color = Color.White, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(4.dp)
        )
        Text(
            text = news.authors?.joinToString(", ") ?: "", color = Color.White, modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(4.dp)
        )

    }
}

@Composable
fun SearchBar(text: String, onSearch: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onSearch,
        label = { Text(text = "Search") },
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth()
    )
}