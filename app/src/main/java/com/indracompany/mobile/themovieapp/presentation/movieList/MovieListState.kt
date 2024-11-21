package com.indracompany.mobile.themovieapp.presentation.movieList

import com.indracompany.mobile.themovieapp.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val upcomingMovieListPage: Int = 1,
    val upcomingMovieList: List<Movie> = emptyList()
)