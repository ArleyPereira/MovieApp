package br.com.hellodev.movieapp.domain.usecase.user

import br.com.hellodev.movieapp.domain.model.user.User
import br.com.hellodev.movieapp.domain.repository.user.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): User {
        return userRepository.getUser()
    }

}