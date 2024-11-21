package com.indracompany.mobile.themovieapp.presentation.movieDetails

import com.indracompany.mobile.themovieapp.domain.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null
)
