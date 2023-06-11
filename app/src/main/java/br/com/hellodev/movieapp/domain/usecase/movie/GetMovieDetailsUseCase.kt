package br.com.hellodev.movieapp.domain.usecase.movie

import br.com.hellodev.movieapp.data.mapper.toDomain
import br.com.hellodev.movieapp.domain.model.Movie
import br.com.hellodev.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(apiKey: String, language: String?, movieId: Int?): Movie {
        return repository.getMovieDetails(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        ).toDomain()
    }

}