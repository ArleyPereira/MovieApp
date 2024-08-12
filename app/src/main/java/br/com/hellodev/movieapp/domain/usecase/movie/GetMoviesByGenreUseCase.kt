package br.com.hellodev.movieapp.domain.usecase.movie

import br.com.hellodev.movieapp.data.model.movie.MovieResponse
import br.com.hellodev.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetMoviesByGenreUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(genreId: Int?): List<MovieResponse> {
        return repository.getMoviesByGenre(genreId).results ?: emptyList()
    }

}