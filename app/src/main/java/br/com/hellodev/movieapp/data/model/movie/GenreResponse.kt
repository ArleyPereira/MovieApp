package br.com.hellodev.movieapp.data.model.movie

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?
)
