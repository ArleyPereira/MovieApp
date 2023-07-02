package br.com.hellodev.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class CreditResponse(

    @SerializedName("cast")
    val cast: List<PersonResponse>?

)
