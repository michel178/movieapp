package com.indracompany.mobile.themovieapp.data.repository_impl

import com.indracompany.mobile.themovieapp.data.local.MovieDatabase
import com.indracompany.mobile.themovieapp.data.mappers.toMovie
import com.indracompany.mobile.themovieapp.data.mappers.toMovieEntity
import com.indracompany.mobile.themovieapp.data.remote.MovieApi
import com.indracompany.mobile.themovieapp.domain.model.Movie
import com.indracompany.mobile.themovieapp.domain.repository.MovieListRepository
import com.indracompany.mobile.themovieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class MovieListRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
) : MovieListRepository {

    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {

            emit(Resource.Loading(true))

            val localMovieList = movieDatabase.movieDao.getMovieList()

            val shouldLoadLocalMovie = localMovieList.isNotEmpty() && !forceFetchFromRemote

            if (shouldLoadLocalMovie) {
                emit(Resource.Success(
                    data = localMovieList.map { movieEntity ->
                        movieEntity.toMovie()
                    }
                ))

                emit(Resource.Loading(false))
                return@flow
            }

            val movieListFromApi = try {

                movieApi.getMoviesList(page)

            } catch (e: IOException) {

                e.printStackTrace()
                emit(Resource.Error(message = "Error de red: ${e.message}"))
                emit(Resource.Loading(false))
                return@flow

            } catch (e: HttpException) {

                e.printStackTrace()
                emit(Resource.Error(message = "Error del servidor: Código ${e.code()}, mensaje: ${e.message()}"))
                emit(Resource.Loading(false))
                return@flow

            } catch (e: Exception) {

                e.printStackTrace()
                emit(Resource.Error(message = "Error inesperado: ${e.message}"))
                emit(Resource.Loading(false))
                return@flow

            }


            val movieEntities = movieListFromApi.results.let {
                it.map { movieDto ->
                    movieDto.toMovieEntity()
                }
            }


            movieDatabase.movieDao.upsertMovieList(movieEntities)


            emit(Resource.Success(
                movieEntities.map {
                    it.toMovie()
                }
            ))


            emit(Resource.Loading(false))

        }
    }

    override suspend fun getMovie(id: Int): Flow<Resource<Movie>> {
        return flow {

            emit(Resource.Loading(true))

            val movieEntity = movieDatabase.movieDao.getMovieById(id)

            if (movieEntity != null) {
                emit(
                    Resource.Success(movieEntity.toMovie())
                )

                emit(Resource.Loading(false))
                return@flow
            }

            emit(Resource.Error("Película no encontrada."))

            emit(Resource.Loading(false))


        }
    }
}