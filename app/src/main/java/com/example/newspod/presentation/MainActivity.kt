package com.example.newspod.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newspod.presentation.bookmark.BookmarkScreen
import com.example.newspod.presentation.home.HomeScreen
import com.example.newspod.presentation.newsdetails.NewsDetailsScreen
import com.example.newspod.ui.theme.NewspodTheme
import com.example.newspod.utils.NavRoute
import dagger.hilt.android.AndroidEntryPoint

sealed class NavScreens(val route:String,val icon:ImageVector,val title:String){
    object home:NavScreens("/home", Icons.Default.Home,"Home")
    object bookmark:NavScreens("/bookmark",Icons.Default.Favorite,"Bookmark")
}
val bottomNavItems= listOf(NavScreens.home,NavScreens.bookmark)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewspodTheme {
                val navController = rememberNavController()
                val isBottomBarVisible = remember { mutableStateOf(true) }
                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(visible = isBottomBarVisible.value) {
                            BottomAppBar {
                                val currentRoute =navController.currentBackStackEntryAsState().value?.destination?.route
                                bottomNavItems.forEach {
                                    NavigationBarItem(
                                        selected = currentRoute == it.route,
                                        label = { Text(text = it.title)},
                                        onClick = { navController.navigate(it.route){
                                            popUpTo(navController.graph.startDestinationId){
                                                saveState=true
                                            }
                                            launchSingleTop=true
                                            restoreState= true
                                        } },
                                        icon = { Image(imageVector = it.icon, contentDescription = null) })
                                }
                            }
                        }
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(navController = navController, startDestination = NavScreens.home.route ){
                            composable(NavScreens.home.route) {
                                HomeScreen(navController = navController)
                                isBottomBarVisible.value=true
                            }
                            composable("/details/news={news}&isLocal={isLocal}") {
                                val newsJson = it.arguments?.getString("news")
                                val isLocal= it.arguments?.getString("isLocal").toBoolean()
                                val news=NavRoute.getNewsFromRoute(newsJson!!)
                                NewsDetailsScreen(navController = navController,news,isLocal?:false)
                                isBottomBarVisible.value=false
                            }
                            composable(NavScreens.bookmark.route) {
                                BookmarkScreen(navController)
                                isBottomBarVisible.value=true
                            }
                        }
                    }
                }
            }
        }
    }
}


