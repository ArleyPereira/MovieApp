package br.com.hellodev.movieapp.domain.usecase.user

import android.net.Uri
import br.com.hellodev.movieapp.domain.repository.user.UserRepository
import javax.inject.Inject

class SaveUserImageUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(uri: Uri): String {
        return userRepository.saveUserImage(uri)
    }

}