package br.com.hellodev.movieapp.data.repository.movie

import androidx.paging.PagingSource
import br.com.hellodev.movieapp.data.api.ServiceApi
import br.com.hellodev.movieapp.data.model.GenresResponse
import br.com.hellodev.movieapp.data.model.MovieResponse
import br.com.hellodev.movieapp.data.paging.MovieByGenrePagingSource
import br.com.hellodev.movieapp.data.paging.SearchMoviePagingSource
import br.com.hellodev.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
): MovieRepository {

    override suspend fun getGenres(): GenresResponse {
        return serviceApi.getGenres()
    }

    override fun getMoviesByGenre(genreId: Int?): PagingSource<Int, MovieResponse> {
        return MovieByGenrePagingSource(serviceApi, genreId)
    }

    override fun searchMovies(query: String?): PagingSource<Int, MovieResponse> {
        return SearchMoviePagingSource(serviceApi, query)
    }

}









