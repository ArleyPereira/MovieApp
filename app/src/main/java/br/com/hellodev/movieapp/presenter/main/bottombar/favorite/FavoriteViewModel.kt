package br.com.hellodev.movieapp.presenter.main.bottombar.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import br.com.hellodev.movieapp.domain.usecase.favorite.GetFavoritesUseCase
import br.com.hellodev.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {

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