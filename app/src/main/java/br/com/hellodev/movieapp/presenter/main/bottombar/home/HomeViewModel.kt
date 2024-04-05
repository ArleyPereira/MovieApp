package br.com.hellodev.movieapp.presenter.main.bottombar.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.hellodev.movieapp.data.mapper.toDomain
import br.com.hellodev.movieapp.domain.model.Genre
import br.com.hellodev.movieapp.domain.usecase.movie.GetGenresUseCase
import br.com.hellodev.movieapp.domain.usecase.movie.GetMoviesByGenreUseCase
import br.com.hellodev.movieapp.presenter.model.MoviesByGenre
import br.com.hellodev.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
) : ViewModel() {

    private val _movieGenreList = MutableLiveData<List<MoviesByGenre>>()
    val movieGenreList: LiveData<List<MoviesByGenre>>
        get() = _movieGenreList

    private val _homeState = MutableLiveData<StateView<Unit>>()
    val homeState: LiveData<StateView<Unit>> get() = _homeState

    init {
        getGenres()
    }

    private fun getGenres() {
        viewModelScope.launch {
            try {
                _homeState.postValue(StateView.Loading())

                val genres = getGenresUseCase()

                getMoviesByGenre(genres = genres)
            } catch (e: Exception) {
                e.printStackTrace()
                _homeState.postValue(StateView.Error(message = e.message))
            }
        }
    }

    private fun getMoviesByGenre(genres: List<Genre>) {
        val moviesByGenre: MutableList<MoviesByGenre> = mutableListOf()
        genres.forEach { genre ->
            viewModelScope.launch {
                try {
                    _homeState.postValue(StateView.Loading())

                    val movies = getMoviesByGenreUseCase(genreId = genre.id)

                    val movieByGenre = MoviesByGenre(
                        id = genre.id,
                        name = genre.name,
                        movies = movies.take(5).map { it.toDomain() }
                    )
                    moviesByGenre.add(movieByGenre)

                    if (moviesByGenre.size == genres.size) {
                        _movieGenreList.postValue(moviesByGenre)
                        _homeState.postValue(StateView.Success(Unit))
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    _homeState.postValue(StateView.Error(message = e.message))
                }
            }
        }

    }

}