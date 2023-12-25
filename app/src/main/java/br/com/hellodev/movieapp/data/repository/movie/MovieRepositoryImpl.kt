package br.com.hellodev.movieapp.data.repository.movie

import androidx.paging.PagingSource
import br.com.hellodev.movieapp.data.api.ServiceApi
import br.com.hellodev.movieapp.data.model.GenresResponse
import br.com.hellodev.movieapp.data.model.MovieResponse
import br.com.hellodev.movieapp.data.paging.MovieByGenrePagingSource
import br.com.hellodev.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
): MovieRepository {

    override suspend fun getGenres(apiKey: String?, language: String?): GenresResponse {
        return serviceApi.getGenres(
            apiKey = apiKey,
            language = language
        )
    }

    override fun getMoviesByGenre(
        apiKey: String?,
        language: String?,
        genreId: Int?
    ): PagingSource<Int, MovieResponse> {
        return MovieByGenrePagingSource(serviceApi, genreId)
    }

    override suspend fun searchMovies(
        apiKey: String?,
        language: String?,
        query: String?
    ): List<MovieResponse> {
        return serviceApi.searchMovies(
            apiKey = apiKey,
            language = language,
            query = query
        ).results ?: emptyList()
    }

}









