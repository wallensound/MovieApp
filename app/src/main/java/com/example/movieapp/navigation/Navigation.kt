package com.example.movieapp.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.account.AccountScreen
import com.example.movieapp.screens.details.DetailsScreen
import com.example.movieapp.screens.details.DetailsScreenTV
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.screens.search.SearchScreen

@Composable
fun Navigation(navController: NavHostController = rememberNavController()) {

    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = Color.White
        ) {
            val selectedHome = remember { mutableStateOf(true) }
            val selectedSearch = remember { mutableStateOf(false) }
            val selectedAccount = remember { mutableStateOf(false) }

            BottomNavigationItem(selected = selectedHome.value, onClick = {
                navController.navigate(Screen.HomeScreen.route)
                selectedHome.value = true
                selectedSearch.value = false
                selectedAccount.value = false
            }, icon = {
                Icon(
                    imageVector = Icons.Default.TrendingUp,
                    contentDescription = "Trending"
                )
            })
            BottomNavigationItem(selected = selectedSearch.value, onClick = {
                navController.navigate(Screen.SearchScreen.route)
                selectedHome.value = false
                selectedSearch.value = true
                selectedAccount.value = false
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Trending"
                )
            })
            BottomNavigationItem(selected = selectedAccount.value, onClick = {
                navController.navigate(Screen.AccountScreen.route)
                selectedHome.value = false
                selectedSearch.value = false
                selectedAccount.value = true
            }, icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Trending"
                )
            })
        }
    }) {
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(it)
        ) {
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(route = Screen.SearchScreen.route) {
                SearchScreen(navController = navController)
            }
            composable(route = Screen.AccountScreen.route) {
                AccountScreen(navController = navController)
            }
            composable(
                route = Screen.DetailsScreen.route + "/{id}",
                arguments = listOf(navArgument("id") {
                    type = NavType.IntType
                })
            ) {
                DetailsScreen(id = it.arguments?.getInt("id"), navController = navController)
            }
            composable(
                route = Screen.DetailsScreenTV.route + "/{id}",
                arguments = listOf(navArgument("id") {
                    type = NavType.IntType
                })
            ) {
                DetailsScreenTV(id = it.arguments?.getInt("id"), navController = navController)
            }
        }
    }
}