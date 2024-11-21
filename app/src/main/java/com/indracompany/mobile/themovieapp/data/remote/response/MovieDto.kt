package com.indracompany.mobile.themovieapp.data.remote.response

data class MovieDto(
    val id: Int?,

    val overview: String?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val vote_average: Double?,
    val backdrop_path: String?
)