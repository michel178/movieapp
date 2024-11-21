package com.indracompany.mobile.themovieapp.domain.model

data class Movie(
    val id: Int,

    val overview: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: Double,
    val backdrop_path: String
)