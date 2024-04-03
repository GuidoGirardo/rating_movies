package com.damaris.rating_movies.view.navigation

sealed class AppScreens(val route: String) {
    object MoviesMain : AppScreens("movies_main")
    object MoviesDetail : AppScreens("movies_detail")
    
}