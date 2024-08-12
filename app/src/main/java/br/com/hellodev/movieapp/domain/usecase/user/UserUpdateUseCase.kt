package br.com.hellodev.movieapp.domain.usecase.user

import br.com.hellodev.movieapp.domain.model.user.User
import br.com.hellodev.movieapp.domain.repository.user.UserRepository
import javax.inject.Inject

class UserUpdateUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(user: User) {
        userRepository.update(user)
    }

}