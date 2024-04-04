package com.damaris.rating_movies.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.damaris.rating_movies.ViewModel.MovieViewModel
import com.damaris.rating_movies.model.MovieEntity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.damaris.rating_movies.view.navigation.AppScreens
import com.damaris.rating_movies.view.ui.theme.background
import com.damaris.rating_movies.view.ui.theme.borderColor
import com.damaris.rating_movies.view.ui.theme.itemColor
import com.damaris.rating_movies.view.ui.theme.textColor

@Composable
fun MoviesMain(navController: NavController, viewModel: MovieViewModel = viewModel()) {

    var movieName by remember { mutableStateOf("") }
    viewModel.getMovies()
    // viewModel.deleteAllMovies()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(vertical = 16.dp)
    ) {
        // primer elemento de la columna
        val movies by viewModel.movies.observeAsState(initial = emptyList())
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(movies) { movie ->
                MovieItem(movie = movie) {
                    navController.navigate("${AppScreens.MoviesDetail.route}/${movie.id}")
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // segundo elemento de la columna (fijado al bottom)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp)),
                value = movieName,
                onValueChange = { movieName = it },
                placeholder = { Text("Ingrese una película") }
            )
            Spacer(modifier = Modifier.width(24.dp))
            Button(
                onClick = {
                    viewModel.insertMovie(movieName)
                },
                modifier = Modifier.background(borderColor, RoundedCornerShape(16.dp)),
                colors = ButtonDefaults.buttonColors(
                    borderColor
                )
            ) {
                Text(
                    "enviar",
                    color = background,
                    modifier = Modifier.background(borderColor, RoundedCornerShape(24.dp)),
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun MovieItem(movie: MovieEntity, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(85.dp)
            .background(itemColor, RoundedCornerShape(16.dp))
            .clickable(onClick = onItemClick)
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            modifier = Modifier.fillMaxHeight() // Para que la Row ocupe todo el alto del Box
        ) {
            Image(
                painter = painterResource(id = movie.coverImageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight() // Para que la imagen ocupe todo el alto del Row
                    .aspectRatio(1f) // Mantener la relación de aspecto de la imagen
                    .clip(RoundedCornerShape(16.dp))
                    .padding(start = 0.dp) // Eliminar cualquier relleno en el borde izquierdo
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .weight(1f) // El texto ocupa el espacio restante en el Row
                    .align(Alignment.CenterVertically) // Para centrar el texto verticalmente
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(8.dp),
                    color = textColor,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}
