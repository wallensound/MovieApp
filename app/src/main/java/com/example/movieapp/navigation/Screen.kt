package com.example.movieapp.navigation

sealed class Screen (val route: String) {
    object HomeScreen : Screen("screens/home/HomeScreen")
    object DetailsScreen : Screen("screens/details/DetailsScreen")
    object DetailsScreenTV : Screen("screens/details/DetailsScreenTV")
    object SearchScreen : Screen("screens/search/SearchScreen")
    object AccountScreen : Screen("screens/account/AccountScreen")
}
