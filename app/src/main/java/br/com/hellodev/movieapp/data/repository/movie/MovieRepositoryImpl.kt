package br.com.hellodev.movieapp.data.repository.movie

import br.com.hellodev.movieapp.data.api.ServiceApi
import br.com.hellodev.movieapp.data.model.GenresResponse
import br.com.hellodev.movieapp.data.model.MovieResponse
import br.com.hellodev.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
): MovieRepository {

    override suspend fun getGenres(apiKey: String, language: String?): GenresResponse {
        return serviceApi.getGenres(
            apiKey = apiKey,
            language = language
        )
    }

    override suspend fun getMoviesByGenre(
        apiKey: String,
        language: String?,
        genreId: Int?
    ): List<MovieResponse> {
        return serviceApi.getMoviesByGenre(
            apiKey = apiKey,
            language = language,
            genreId = genreId
        ).results ?: emptyList()
    }
}









