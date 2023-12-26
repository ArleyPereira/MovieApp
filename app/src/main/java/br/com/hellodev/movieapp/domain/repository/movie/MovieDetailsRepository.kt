package br.com.hellodev.movieapp.domain.repository.movie

import br.com.hellodev.movieapp.data.model.CreditResponse
import br.com.hellodev.movieapp.data.model.MovieResponse
import br.com.hellodev.movieapp.data.model.MovieReviewResponse

interface MovieDetailsRepository {

    suspend fun getMovieDetails(movieId: Int?): MovieResponse

    suspend fun getCredits(movieId: Int?): CreditResponse

    suspend fun getSimilar(movieId: Int?): List<MovieResponse>

    suspend fun getMovieReviews(movieId: Int?): List<MovieReviewResponse>

}