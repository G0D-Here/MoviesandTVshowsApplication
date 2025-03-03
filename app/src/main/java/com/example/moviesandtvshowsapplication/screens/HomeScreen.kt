package com.example.moviesandtvshowsapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moviesandtvshowsapplication.data.Movie
import com.example.moviesandtvshowsapplication.navigation.DetailsScreen
import com.example.moviesandtvshowsapplication.viewmodel.MovieViewModel

@Composable
fun HomeScreen(vm: MovieViewModel = hiltViewModel(), navController: NavController) {

    /* val movies by vm.moviesList.collectAsState()
    val shows by vm.showsList.collectAsState()

    val clicked = remember { mutableStateOf(true) }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Movies",
                Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { clicked.value = true }
                    .background(if (clicked.value) Color.LightGray else Color.White)
                    .padding(6.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "TV & Shows",
                Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        clicked.value = false
                    }
                    .background(if (!clicked.value) Color.LightGray else Color.White)
                    .padding(6.dp),
                fontWeight = FontWeight.Bold
            )
        }

        if (clicked.value) {
            when {
                shows.loading == true -> {
                    CircularProgressIndicator()
                }

                shows.data != null -> {
                    val moviesList = shows.data!!.titles
                    ShowAndMovieCard(moviesList, navController)
                }

                shows.e != null -> {
                    Text(text = "Error")
                    Text(text = movies.e!!.message.toString())
                }
            }

        } else {
            when {
                movies.loading == true -> {
                    CircularProgressIndicator()
                }

                movies.data != null -> {
                    val moviesList = movies.data!!.titles
                    ShowAndMovieCard(moviesList, navController)
                }

                movies.e != null -> {
                    Text(text = "Error")
                    Text(text = movies.e!!.message.toString())
                }
            }
        }
    }*/
    val clicked: MutableState<Boolean?> = remember { mutableStateOf(null) }
    LaunchedEffect(Unit) {
        vm.getMovies("movie")
        vm.getShows("tv_series")
    }
    val movies = vm.moviesList.value
    val shows = vm.showsList.value

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Movies",
                Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        clicked.value = true
                        vm.getMovies("movie")
                    }
                    .background(if (clicked.value == true) Color.LightGray else Color.White)
                    .padding(6.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "TV & Shows",
                Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .clickable {
                        clicked.value = false
                    }
                    .background(if (clicked.value == false) Color.LightGray else Color.White)
                    .padding(6.dp),
                fontWeight = FontWeight.Bold
            )
        }
        if (clicked.value ==true) {
            when {
                movies != null -> {
                    val moviesList = movies.value!!.titles
                    ShowAndMovieCard(moviesList, navController)
                }

                else -> {
                    Button(onClick = {
                        vm.getMovies("movie")
                    }) { }
                    Text(text = "Pick one")
                }
            }
        } else {
            if (shows != null) {
                val moviesList = shows.titles
                ShowAndMovieCard(moviesList, navController)
            } else {
                CircularProgressIndicator()
            }
        }
    }
}


@Composable
fun ShowAndMovieCard(movies: List<Movie>, navController: NavController) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        items(movies) { movie ->

            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = movie.title,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(text = "type: " + movie.type, fontWeight = FontWeight.W500)
                    Text(text = "year: " + movie.year.toString())
                }
                val id = movie.id
                Button(onClick = { navController.navigate(DetailsScreen(id)) }) {
                    Text(text = "Details")
                }
            }

            Spacer(Modifier.height(10.dp))
            HorizontalDivider()
            Spacer(Modifier.height(10.dp))
        }
    }
}