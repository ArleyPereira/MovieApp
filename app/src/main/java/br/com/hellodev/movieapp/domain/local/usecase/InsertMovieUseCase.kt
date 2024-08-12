package br.com.hellodev.movieapp.domain.local.usecase

import br.com.hellodev.movieapp.data.mapper.toEntity
import br.com.hellodev.movieapp.domain.local.repository.MovieLocalRepository
import br.com.hellodev.movieapp.domain.model.movie.Movie
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(
    private val repository: MovieLocalRepository
) {

    suspend operator fun invoke(movie: Movie) {
        return repository.insertMovie(movie.toEntity())
    }

}