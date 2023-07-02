package br.com.hellodev.movieapp.domain.usecase.movie

import br.com.hellodev.movieapp.data.mapper.toDomain
import br.com.hellodev.movieapp.domain.model.Credit
import br.com.hellodev.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class GetCreditsUseCase @Inject constructor(
    private val repository: MovieDetailsRepository
) {

    suspend operator fun invoke(apiKey: String, language: String?, movieId: Int?): Credit {
        return repository.getCredits(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        ).toDomain()
    }

}