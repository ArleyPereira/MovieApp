package br.com.hellodev.movieapp.presenter.main.bottombar.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.hellodev.movieapp.domain.model.movie.Movie
import br.com.hellodev.movieapp.domain.usecase.movie.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
): ViewModel() {

    fun searchMovies(query: String?): Flow<PagingData<Movie>> {
        return searchMoviesUseCase(query = query).cachedIn(viewModelScope)
    }

}