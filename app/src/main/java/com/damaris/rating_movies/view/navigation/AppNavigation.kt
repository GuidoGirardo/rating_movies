package com.damaris.rating_movies.view.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.damaris.rating_movies.view.screens.MoviesDetail
import com.damaris.rating_movies.view.screens.MoviesMain

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MoviesMain.route){
        composable(route = AppScreens.MoviesMain.route){
            MoviesMain(navController)
        }
        composable(route = AppScreens.MoviesDetail.route){
            MoviesDetail(navController)
        }
    }
}