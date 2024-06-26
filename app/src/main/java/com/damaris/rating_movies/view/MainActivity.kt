package com.damaris.rating_movies.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.damaris.rating_movies.ViewModel.MovieViewModel
import com.damaris.rating_movies.view.navigation.AppNavigation
import com.damaris.rating_movies.view.ui.theme.Rating_moviesTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.initialize(applicationContext)

        setContent {
            Rating_moviesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Defaultreview() {
    Rating_moviesTheme {
        AppNavigation()
    }
}