package br.com.hellodev.movieapp.domain.repository.movie

import androidx.paging.PagingSource
import br.com.hellodev.movieapp.data.model.BasePaginationRemote
import br.com.hellodev.movieapp.data.model.GenresResponse
import br.com.hellodev.movieapp.data.model.MovieResponse

interface MovieRepository {

    suspend fun getGenres(): GenresResponse

    fun getMoviesByGenrePagination(genreId: Int?): PagingSource<Int, MovieResponse>

    suspend fun getMoviesByGenre(genreId: Int?): BasePaginationRemote<List<MovieResponse>>

    fun searchMovies(query: String?): PagingSource<Int, MovieResponse>

}