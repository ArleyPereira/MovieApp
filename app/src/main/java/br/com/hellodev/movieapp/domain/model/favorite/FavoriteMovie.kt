package br.com.hellodev.movieapp.domain.model.favorite

import android.os.Parcelable
import br.com.hellodev.movieapp.domain.model.movie.Genre
import kotlinx.parcelize.Parcelize

@Parcelize
data class FavoriteMovie(
    val genres: List<Genre>? = null,
    val id: Int? = null,
    val posterPath: String? = null,
    val title: String? = null,
    val voteAverage: String? = null
) : Parcelable
