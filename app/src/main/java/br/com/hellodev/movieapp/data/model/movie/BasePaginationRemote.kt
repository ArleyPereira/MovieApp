package br.com.hellodev.movieapp.data.model.movie

import com.google.gson.annotations.SerializedName

data class BasePaginationRemote<out T>(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("results")
    val results: T?,

    @SerializedName("total_results")
    val totalResults: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?
)
