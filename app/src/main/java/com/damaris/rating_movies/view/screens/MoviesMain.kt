package com.damaris.rating_movies.view.screens

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.damaris.rating_movies.view.navigation.AppScreens

@Composable
fun MoviesMain(navController: NavController) {

    Text("hola 1",
        modifier = Modifier.clickable {
            navController.navigate(route = AppScreens.MoviesDetail.route)
        })

}