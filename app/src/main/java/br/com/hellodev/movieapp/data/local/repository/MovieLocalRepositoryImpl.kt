package br.com.hellodev.movieapp.data.local.repository

import br.com.hellodev.movieapp.data.local.dao.MovieDao
import br.com.hellodev.movieapp.data.local.entity.MovieEntity
import br.com.hellodev.movieapp.domain.local.repository.MovieLocalRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieLocalRepositoryImpl @Inject constructor(
    private val movieDao: MovieDao
): MovieLocalRepository {

    override fun getMovies(): Flow<List<MovieEntity>> {
        return movieDao.getMovies()
    }

    override suspend fun insertMovie(movieEntity: MovieEntity) {
        movieDao.insertMovie(movieEntity)
    }

    override suspend fun deleteMovie(movieId: Int?) {
        movieDao.deleteMovie(movieId)
    }
}