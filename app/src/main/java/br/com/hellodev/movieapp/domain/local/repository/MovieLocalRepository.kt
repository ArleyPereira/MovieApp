package br.com.hellodev.movieapp.domain.local.repository

import br.com.hellodev.movieapp.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieLocalRepository {

    fun getMovies(): Flow<List<MovieEntity>>

    suspend fun insertMovie(movieEntity: MovieEntity)

    suspend fun deleteMovie(movieId: Int?)

}