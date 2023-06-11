package br.com.hellodev.movieapp.presenter.main.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.com.hellodev.movieapp.BuildConfig
import br.com.hellodev.movieapp.domain.usecase.movie.GetMovieDetailsUseCase
import br.com.hellodev.movieapp.util.Constants
import br.com.hellodev.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
): ViewModel() {

    fun getMovieDetails(movieId: Int?) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val result = getMovieDetailsUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Constants.Movie.LANGUAGE,
                movieId = movieId
            )

            emit(StateView.Success(result))

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

}