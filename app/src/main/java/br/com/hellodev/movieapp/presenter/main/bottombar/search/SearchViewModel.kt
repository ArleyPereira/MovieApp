package br.com.hellodev.movieapp.presenter.main.bottombar.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.movieapp.domain.model.Movie
import br.com.hellodev.movieapp.domain.usecase.movie.SearchMoviesUseCase
import br.com.hellodev.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
): ViewModel() {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    private val _searchState = MutableLiveData<StateView<Unit>>()
    val searchState: LiveData<StateView<Unit>> get() = _searchState

    fun searchMovies(query: String?) {
        viewModelScope.launch {
            try {
                _searchState.postValue(StateView.Loading())

                val movies = searchMoviesUseCase(query = query)

                //_movieList.postValue(movies)
                _searchState.postValue(StateView.Success(Unit))

            } catch (e: HttpException) {
                e.printStackTrace()
                _searchState.postValue(StateView.Error(message = e.message))
            } catch (e: Exception) {
                e.printStackTrace()
                _searchState.postValue(StateView.Error(message = e.message))
            }
        }
    }

}