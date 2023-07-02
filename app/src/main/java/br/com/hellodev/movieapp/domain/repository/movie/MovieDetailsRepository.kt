package br.com.hellodev.movieapp.domain.repository.movie

import br.com.hellodev.movieapp.data.model.CreditResponse
import br.com.hellodev.movieapp.data.model.MovieResponse

interface MovieDetailsRepository {

    suspend fun getMovieDetails(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): MovieResponse

    suspend fun getCredits(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): CreditResponse

}