package com.indracompany.mobile.themovieapp.domain.repository

import com.indracompany.mobile.themovieapp.domain.model.Movie
import com.indracompany.mobile.themovieapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieListRepository {
    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getMovie(id: Int): Flow<Resource<Movie>>
}