package br.com.hellodev.movieapp.domain.repository.user

import android.net.Uri
import br.com.hellodev.movieapp.domain.model.user.User

interface UserRepository {

    suspend fun update(user: User)

    suspend fun saveUserImage(uri: Uri): String

    suspend fun getUser(): User

}