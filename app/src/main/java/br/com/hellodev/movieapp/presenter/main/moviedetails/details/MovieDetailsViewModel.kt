package br.com.hellodev.movieapp.presenter.main.moviedetails.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.com.hellodev.movieapp.domain.local.usecase.InsertMovieUseCase
import br.com.hellodev.movieapp.domain.model.favorite.FavoriteMovie
import br.com.hellodev.movieapp.domain.model.movie.Movie
import br.com.hellodev.movieapp.domain.usecase.favorite.GetFavoritesUseCase
import br.com.hellodev.movieapp.domain.usecase.favorite.SaveFavoritesUseCase
import br.com.hellodev.movieapp.domain.usecase.movie.GetCreditsUseCase
import br.com.hellodev.movieapp.domain.usecase.movie.GetMovieDetailsUseCase
import br.com.hellodev.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getCreditsUseCase: GetCreditsUseCase,
    private val insertMovieUseCase: InsertMovieUseCase,
    private val saveFavoritesUseCase: SaveFavoritesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

    private val _movieId = MutableLiveData(0)
    val movieId: LiveData<Int> = _movieId

    fun getMovieDetails(movieId: Int?) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val result = getMovieDetailsUseCase.invoke(movieId = movieId)

            emit(StateView.Success(result))

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

    fun getCredits(movieId: Int?) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val result = getCreditsUseCase(movieId = movieId)

            emit(StateView.Success(result))

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

    fun insertMovie(movie: Movie) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            insertMovieUseCase(movie)

            emit(StateView.Success(Unit))

        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

    fun setMovieId(movieId: Int) {
        _movieId.value = movieId
    }

    fun saveFavorites(favorites: List<FavoriteMovie>) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            saveFavoritesUseCase(favorites)

            emit(StateView.Success(Unit))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        }
    }

    fun getFavorites() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val favorites = getFavoritesUseCase()

            emit(StateView.Success(favorites))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(StateView.Error(message = exception.message))
        }
    }

}