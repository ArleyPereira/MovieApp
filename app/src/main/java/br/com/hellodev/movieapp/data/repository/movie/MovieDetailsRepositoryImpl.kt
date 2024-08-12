package br.com.hellodev.movieapp.data.repository.movie

import br.com.hellodev.movieapp.data.api.ServiceApi
import br.com.hellodev.movieapp.data.model.movie.CreditResponse
import br.com.hellodev.movieapp.data.model.movie.MovieResponse
import br.com.hellodev.movieapp.data.model.movie.MovieReviewResponse
import br.com.hellodev.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
): MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Int?): MovieResponse {
        return serviceApi.getMovieDetails(
            movieId = movieId
        )
    }

    override suspend fun getCredits(
        movieId: Int?
    ): CreditResponse {
        return serviceApi.getCredits(movieId = movieId)
    }

    override suspend fun getSimilar(movieId: Int?
    ): List<MovieResponse> {
        return serviceApi.getSimilar(
            movieId = movieId
        ).results ?: emptyList()
    }

    override suspend fun getMovieReviews(
        movieId: Int?
    ): List<MovieReviewResponse> {
        return serviceApi.getMovieReviews(
            movieId = movieId
        ).results ?: emptyList()
    }

}









