package com.damaris.rating_movies.view.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.damaris.rating_movies.R
import com.damaris.rating_movies.ViewModel.MovieViewModel
import com.damaris.rating_movies.view.ui.theme.background
import com.damaris.rating_movies.view.ui.theme.borderColor
import com.damaris.rating_movies.view.ui.theme.textColor


@Composable
fun MoviesDetail(navController: NavController, viewModel: MovieViewModel, movieId: Int) {

    var title by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf(0) }
    var portada by remember { mutableStateOf(R.drawable.imagen_0) }

    var currentNumber by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = movieId) {
        val movie = viewModel.getMovieById(movieId)

        if (movie != null) {
            title = movie.title
            rating = movie.rating
            portada = movie.coverImageResourceId
            currentNumber = movie.numberPortada
        } else {
            title = "Película no encontrada"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Centrar todo horizontalmente
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Título: $title",
            color = textColor,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Rating: $rating",
            color = textColor,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Slider(
            value = rating.toFloat(),
            onValueChange = { newRating ->
                rating = newRating.toInt()
                viewModel.changeMovieRating(movieId, rating)
            },
            valueRange = 0f..10f,
            steps = 11,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .padding(bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(id = getImageResourceId(currentNumber)),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .clickable {
                        currentNumber = (currentNumber + 1) % 11
                        viewModel.changeImage(getImageResourceId(currentNumber), movieId)
                        viewModel.actualizarNumberPortada(movieId, currentNumber)
                    }
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Box(

        ) {
            Button(
                onClick = {
                    viewModel.deleteMovie(movieId)
                    navController.navigate("movies_main")
                },
                modifier = Modifier.background(borderColor, RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(
                    borderColor
                )
            ) {
                Text(
                    "delete",
                    color = background,
                    modifier = Modifier.background(borderColor, RoundedCornerShape(24.dp)),
                    fontSize = 18.sp
                )
            }
        }
    }



}

private fun getImageResourceId(number: Int): Int {
    return R.drawable::class.java.getField("imagen_$number").getInt(null)
}