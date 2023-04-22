package br.com.hellodev.movieapp.presenter.main.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.com.hellodev.movieapp.BuildConfig
import br.com.hellodev.movieapp.data.mapper.toPresentation
import br.com.hellodev.movieapp.domain.usecase.movie.GetGenresUseCase
import br.com.hellodev.movieapp.domain.usecase.movie.GetMoviesByGenreUseCase
import br.com.hellodev.movieapp.util.Constants.*
import br.com.hellodev.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getMoviesByGenreUseCase: GetMoviesByGenreUseCase
) : ViewModel() {

    fun getGenres() = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val genres = getGenresUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Movie.LANGUAGE
            ).map { it.toPresentation() }

            emit(StateView.Success(genres))

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

    fun getMoviesByGenre(genreId: Int?) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val movies = getMoviesByGenreUseCase.invoke(
                apiKey = BuildConfig.API_KEY,
                language = Movie.LANGUAGE,
                genreId = genreId
            )

            emit(StateView.Success(movies))

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

}