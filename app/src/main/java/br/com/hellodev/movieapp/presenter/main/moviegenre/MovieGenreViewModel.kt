package br.com.hellodev.movieapp.presenter.main.moviegenre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import br.com.hellodev.movieapp.domain.model.movie.Movie
import br.com.hellodev.movieapp.domain.usecase.movie.GetMoviesByGenrePaginationUseCase
import br.com.hellodev.movieapp.domain.usecase.movie.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieGenreViewModel @Inject constructor(
    private val getMoviesByGenrePaginationUseCase: GetMoviesByGenrePaginationUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val _movieList = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val movieList
        get() = _movieList.asStateFlow()

    private var currentGenreId: Int? = null

    fun getMoviesByGenrePagination(genreId: Int?, forceRequest: Boolean) = viewModelScope.launch {
        if (genreId != currentGenreId || forceRequest) {
            currentGenreId = genreId
            getMoviesByGenrePaginationUseCase(genreId = genreId).cachedIn(viewModelScope).collectLatest {
                _movieList.emit(it)
            }
        }
    }

    fun searchMovies(query: String?): Flow<PagingData<Movie>>  {
        return searchMoviesUseCase(query = query).cachedIn(viewModelScope)
    }

}