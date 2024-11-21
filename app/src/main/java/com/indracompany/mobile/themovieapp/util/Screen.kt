package com.indracompany.mobile.themovieapp.util

sealed class Screen(val rout: String) {
    object Home : Screen("main")
    object UpcomingMovieList : Screen("upcomingMovie")
    object Details : Screen("details")
}