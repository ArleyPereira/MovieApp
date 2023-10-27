package br.com.hellodev.movieapp.domain.local.usecase

import br.com.hellodev.movieapp.data.mapper.toDomain
import br.com.hellodev.movieapp.domain.local.repository.MovieLocalRepository
import br.com.hellodev.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieLocalRepository
) {

    operator fun invoke(): Flow<List<Movie>> {
        return repository.getMovies().map { movieList ->
            movieList.map { it.toDomain() }
        }
    }

}