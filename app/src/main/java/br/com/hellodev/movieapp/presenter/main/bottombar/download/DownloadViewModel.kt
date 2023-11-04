package br.com.hellodev.movieapp.presenter.main.bottombar.download

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.movieapp.domain.local.usecase.GetMoviesUseCase
import br.com.hellodev.movieapp.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DownloadViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _movieList = MutableLiveData(mutableListOf<Movie>())
    var movieList: LiveData<MutableList<Movie>> = _movieList

    fun getMovies() = viewModelScope.launch {
        getMoviesUseCase().collect { movies ->
            _movieList.postValue(movies.toMutableList())
        }
    }

}