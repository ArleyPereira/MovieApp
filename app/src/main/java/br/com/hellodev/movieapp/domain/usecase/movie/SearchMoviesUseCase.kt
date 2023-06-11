package br.com.hellodev.movieapp.domain.usecase.movie

import br.com.hellodev.movieapp.data.mapper.toDomain
import br.com.hellodev.movieapp.domain.model.Movie
import br.com.hellodev.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(apiKey: String, language: String?, query: String?): List<Movie> {
        return repository.searchMovies(
            apiKey = apiKey,
            language = language,
            query = query
        ).filter { it.backdropPath != null }.map { it.toDomain() }
    }

}