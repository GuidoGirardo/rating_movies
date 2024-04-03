package com.damaris.rating_movies.view.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.damaris.rating_movies.ViewModel.MovieViewModel
import com.damaris.rating_movies.view.screens.MoviesDetail
import com.damaris.rating_movies.view.screens.MoviesMain

@Composable
fun AppNavigation(viewModel: MovieViewModel = viewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.MoviesMain.route) {
        composable(route = AppScreens.MoviesMain.route) {
            MoviesMain(navController, viewModel)
        }
        composable(
            route = "${AppScreens.MoviesDetail.route}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: -1
            MoviesDetail(navController, viewModel, movieId)
        }
    }
}