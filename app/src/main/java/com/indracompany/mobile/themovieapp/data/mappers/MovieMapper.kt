package com.indracompany.mobile.themovieapp.data.mappers

import com.indracompany.mobile.themovieapp.data.local.MovieEntity
import com.indracompany.mobile.themovieapp.data.remote.response.MovieDto
import com.indracompany.mobile.themovieapp.domain.model.Movie


fun MovieDto.toMovieEntity(
): MovieEntity {
    return MovieEntity(
        id = id ?: -1,

        overview = overview ?: "",
        poster_path = poster_path ?: "",
        release_date = release_date ?: "",
        title = title ?: "",
        vote_average = vote_average ?: 0.0,
        backdrop_path = backdrop_path ?: ""
    )
}


fun MovieEntity.toMovie(
): Movie {
    return Movie(
        id = id,

        overview = overview,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        vote_average = vote_average,
        backdrop_path = backdrop_path
    )
}

