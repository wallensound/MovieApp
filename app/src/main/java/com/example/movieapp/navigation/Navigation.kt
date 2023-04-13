package com.example.movieapp.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.account.AccountScreen
import com.example.movieapp.screens.details.DetailsScreen
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.screens.search.SearchScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Navigation() {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White
        ) {
            BottomNavigationItem(selected = true, onClick = {
                navController.navigate(Screen.HomeScreen.route)
            }, icon = {
                Icon(
                    imageVector = Icons.Default.TrendingUp,
                    contentDescription = "Trending"
                )
            })
            BottomNavigationItem(selected = false, onClick = {
                navController.navigate(Screen.SearchScreen.route)
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Trending"
                )
            })
            BottomNavigationItem(selected = false, onClick = {
                navController.navigate(Screen.AccountScreen.route)
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Trending"
                )
            })
        }
    }) {
        NavHost(navController = navController, startDestination = Screen.HomeScreen.route, modifier = Modifier.padding(it)) {
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(route = Screen.SearchScreen.route) {
                SearchScreen()
            }
            composable(route = Screen.AccountScreen.route) {
                AccountScreen()
            }
            composable(route = Screen.DetailsScreen.route+"/{id}", arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })) {
                DetailsScreen(id = it.arguments?.getInt("id"), navController = navController)
            }
        }
    }
}