package br.com.hellodev.movieapp.presenter.model

import android.os.Parcelable
import br.com.hellodev.movieapp.domain.model.movie.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesByGenre(
    val id: Int?,
    val name: String?,
    val movies: List<Movie>?
): Parcelable
