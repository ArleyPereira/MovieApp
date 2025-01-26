package br.com.hellodev.movieapp.domain.usecase.favorite

import br.com.hellodev.movieapp.domain.model.favorite.FavoriteMovie
import br.com.hellodev.movieapp.domain.repository.movie.FavoriteMovieRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(
    private val repository: FavoriteMovieRepository
) {

    suspend operator fun invoke(): List<FavoriteMovie> {
        return repository.getFavorites()
    }

}