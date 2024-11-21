package com.indracompany.mobile.themovieapp.presentation.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.indracompany.mobile.themovieapp.R
import com.indracompany.mobile.themovieapp.presentation.movieList.MovieListViewModel
import com.indracompany.mobile.themovieapp.presentation.movieList.UpcomingMoviesScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {

    val movieListViewModel = hiltViewModel<MovieListViewModel>()
    val movieListState = movieListViewModel.movieListState.collectAsState().value

    Scaffold( topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.upcoming_movies),
                    fontSize = 20.sp
                )
            },
            modifier = Modifier.shadow(5.dp),
            colors = TopAppBarDefaults.smallTopAppBarColors(
                MaterialTheme.colorScheme.inverseOnSurface
            )
        )
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            UpcomingMoviesScreen(
                navController = navController,
                movieListState = movieListState,
                onEvent = movieListViewModel::onEvent
            )
        }
    }

}

