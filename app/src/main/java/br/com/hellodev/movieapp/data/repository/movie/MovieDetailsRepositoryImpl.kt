package br.com.hellodev.movieapp.data.repository.movie

import br.com.hellodev.movieapp.data.api.ServiceApi
import br.com.hellodev.movieapp.data.model.CreditResponse
import br.com.hellodev.movieapp.data.model.MovieResponse
import br.com.hellodev.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
): MovieDetailsRepository {

    override suspend fun getMovieDetails(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): MovieResponse {
        return serviceApi.getMovieDetails(
            movieId = movieId,
            language = language,
            apiKey = apiKey
        )
    }

    override suspend fun getCredits(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): CreditResponse {
        return serviceApi.getCredits(
            movieId = movieId,
            language = language,
            apiKey = apiKey
        )
    }

    override suspend fun getSimilar(
        apiKey: String?,
        language: String?,
        movieId: Int?
    ): List<MovieResponse> {
        return serviceApi.getSimilar(
            movieId = movieId,
            language = language,
            apiKey = apiKey
        ).results ?: emptyList()
    }

}









