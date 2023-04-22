package br.com.hellodev.movieapp.data.api

import br.com.hellodev.movieapp.data.model.BasePaginationRemote
import br.com.hellodev.movieapp.data.model.GenresResponse
import br.com.hellodev.movieapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key") apiKey: String,
        @Query("language") language: String?,
    ): GenresResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key") apiKey: String,
        @Query("language") language: String?,
        @Query("with_genres") genreId: Int?,
    ): BasePaginationRemote<List<MovieResponse>>

}