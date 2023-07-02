package br.com.hellodev.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Credit(

    val cast: List<Person>?

) : Parcelable
