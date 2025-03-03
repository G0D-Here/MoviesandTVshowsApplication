package com.example.moviesandtvshowsapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.moviesandtvshowsapplication.navigation.NavHome
import com.example.moviesandtvshowsapplication.ui.theme.MoviesAndTVShowsApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MoviesAndTVShowsApplicationTheme {
                NavHome()
            }
        }
    }
}