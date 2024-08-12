package br.com.hellodev.movieapp.domain.model.user

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import kotlinx.parcelize.Parcelize

@Parcelize
@IgnoreExtraProperties
data class User(
    val id: String? = "",
    val photoUrl: String? = "",
    val firstName: String? = "",
    val surName: String? = "",
    val email: String? = "",
    val phone: String? = "",
    val genre: String? = "",
    val country: String? = ""
) : Parcelable
