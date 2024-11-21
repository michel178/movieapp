package com.indracompany.mobile.themovieapp.presentation.movieList

sealed interface MovieListUiEvent {
    object Paginate : MovieListUiEvent
}