package br.com.hellodev.movieapp.domain.repository.movie

import br.com.hellodev.movieapp.domain.model.favorite.FavoriteMovie

interface FavoriteMovieRepository {

    suspend fun saveFavorites(favorites: List<FavoriteMovie>)

    suspend fun getFavorites(): List<FavoriteMovie>

}