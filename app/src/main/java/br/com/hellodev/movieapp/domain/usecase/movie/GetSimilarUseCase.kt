package br.com.hellodev.movieapp.domain.usecase.movie

import br.com.hellodev.movieapp.data.mapper.toDomain
import br.com.hellodev.movieapp.domain.model.Movie
import br.com.hellodev.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class GetSimilarUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) {

    suspend operator fun invoke(apiKey: String, language: String?, movieId: Int?): List<Movie> {
        return repository.getSimilar(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        ).map { it.toDomain() }.filter { it.posterPath != null }
    }

}