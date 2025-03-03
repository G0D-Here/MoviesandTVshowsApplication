package com.example.moviesandtvshowsapplication.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.moviesandtvshowsapplication.data.Movie
import com.example.moviesandtvshowsapplication.viewmodel.MovieViewModel

@Composable
fun DetailsScreen(
    viewModel: MovieViewModel = hiltViewModel(),
    navController: NavController,
    id: Int
) {
    LaunchedEffect(Unit) {
        viewModel.getDetails(id)
    }
    val movie = viewModel.details.value
    Log.d("DETAILSCREEN", "DetailsScreen: $movie")
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Details",
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold
            )
            Button(onClick = { navController.navigateUp() }) {
                Text(text = "back")
            }
        }
        DetailsCard(movie)
    }
}

@Composable
fun DetailsCard(movie: Movie?) {
    if (movie != null) {
        AsyncImage(
            model = movie.poster,
            contentDescription = "poster",
            Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(12.dp),
            contentScale = ContentScale.Fit
        )
    }
    if (movie != null) {
        Text(
            text = movie.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
    if (movie != null) {
        Text(text = movie.plot_overview, fontFamily = FontFamily.Serif)
    }
    if (movie != null) {
        Text(
            text = "Release date " + movie.release_date,
            fontWeight = FontWeight.W300
        )
    }


}