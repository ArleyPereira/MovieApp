package br.com.hellodev.movieapp.domain.model.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val adult: Boolean? = null,
    val backdropPath: String? = null,
    val genres: List<Genre>? = null,
    val id: Int? = null,
    val originalLanguage: String? = null,
    val originalTitle: String? = null,
    val overview: String? = null,
    val popularity: Float? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val voteAverage: Float? = null,
    val voteCount: Int? = null,
    val productionCountries: List<Country>? = null,
    val runtime: Int? = null
): Parcelable
