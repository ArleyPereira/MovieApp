package br.com.hellodev.movieapp.domain.usecase.auth

import br.com.hellodev.movieapp.domain.repository.auth.FirebaseAuthentication
import javax.inject.Inject

class ForgotUseCase @Inject constructor(
    private val firebaseAuthentication: FirebaseAuthentication
) {

    suspend operator fun invoke(email: String) {
        firebaseAuthentication.forgot(email)
    }

}