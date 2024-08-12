package br.com.hellodev.movieapp.domain.repository.movie

import androidx.paging.PagingSource
import br.com.hellodev.movieapp.data.model.movie.BasePaginationRemote
import br.com.hellodev.movieapp.data.model.movie.GenresResponse
import br.com.hellodev.movieapp.data.model.movie.MovieResponse

interface MovieRepository {

    suspend fun getGenres(): GenresResponse

    fun getMoviesByGenrePagination(genreId: Int?): PagingSource<Int, MovieResponse>

    suspend fun getMoviesByGenre(genreId: Int?): BasePaginationRemote<List<MovieResponse>>

    fun searchMovies(query: String?): PagingSource<Int, MovieResponse>

}