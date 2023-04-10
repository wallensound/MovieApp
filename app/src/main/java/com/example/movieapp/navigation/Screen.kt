package com.example.movieapp.navigation

sealed class Screen (val route: String) {
    object HomeScreen : Screen("screens/home/HomeScreen")
    object DetailsScreen : Screen("screens/details/DetailsScreen")
    object SearchScreen : Screen("screens/search/SearchScreen")
    object AccountScreen : Screen("screens/account/AccountScreen")
}
