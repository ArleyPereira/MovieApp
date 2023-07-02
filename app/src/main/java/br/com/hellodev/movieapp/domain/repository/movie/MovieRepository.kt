package br.com.hellodev.movieapp.domain.repository.movie

import br.com.hellodev.movieapp.data.model.GenresResponse
import br.com.hellodev.movieapp.data.model.MovieResponse

interface MovieRepository {

    suspend fun getGenres(apiKey: String?, language: String?): GenresResponse

    suspend fun getMoviesByGenre(
        apiKey: String?,
        language: String?,
        genreId: Int?
    ): List<MovieResponse>

    suspend fun searchMovies(
        apiKey: String?,
        language: String?,
        query: String?
    ): List<MovieResponse>

}