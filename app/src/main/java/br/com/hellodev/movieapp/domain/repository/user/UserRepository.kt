package br.com.hellodev.movieapp.domain.repository.user

import br.com.hellodev.movieapp.domain.model.user.User

interface UserRepository {

    suspend fun update(user: User)

    suspend fun getUser(): User

}