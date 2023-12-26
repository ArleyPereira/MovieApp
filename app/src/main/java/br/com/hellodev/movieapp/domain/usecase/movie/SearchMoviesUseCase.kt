package br.com.hellodev.movieapp.domain.usecase.movie

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import br.com.hellodev.movieapp.data.mapper.toDomain
import br.com.hellodev.movieapp.domain.model.Movie
import br.com.hellodev.movieapp.domain.repository.movie.MovieRepository
import br.com.hellodev.movieapp.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    operator fun invoke(
        apiKey: String,
        language: String?,
        query: String?
    ): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(
            pageSize = Constants.Paging.NETWORK_PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = Constants.Paging.DEFAULT_PAGE_INDEX
        ),
        pagingSourceFactory = {
            repository.searchMovies(
                apiKey = apiKey,
                language = language,
                query = query
            )
        }
    ).flow.map { pagingData ->
        pagingData.map { movieResponse ->
            movieResponse.toDomain()
        }
    }

}