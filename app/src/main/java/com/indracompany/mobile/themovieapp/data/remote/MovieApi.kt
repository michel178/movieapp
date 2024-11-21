package com.indracompany.mobile.themovieapp.data.remote

import com.indracompany.mobile.themovieapp.data.remote.response.MovieListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/upcoming")
    suspend fun getMoviesList(
        @Query("page") page: Int,
        @Query("language") language: String = "es-MX",
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto


    companion object {

        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "02b7d885136284621c80fa6c8032a2c8"
    }

}