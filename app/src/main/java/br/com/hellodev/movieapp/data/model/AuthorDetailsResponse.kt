package br.com.hellodev.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class AuthorDetailsResponse(
    @SerializedName("name")
    val name: String?,

    @SerializedName("username")
    val username: String?,

    @SerializedName("avatar_path")
    val avatarPath: String?,

    @SerializedName("name")
    val rating: Int?
)
