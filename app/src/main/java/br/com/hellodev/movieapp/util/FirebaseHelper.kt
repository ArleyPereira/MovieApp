package br.com.hellodev.movieapp.util

import br.com.hellodev.movieapp.R
import com.google.firebase.auth.FirebaseAuth

class FirebaseHelper {

    companion object {
        fun getAuth() = FirebaseAuth.getInstance()

        fun isAuthenticated() = getAuth().currentUser != null

        fun getUserId() = FirebaseAuth.getInstance().currentUser?.uid ?: ""

        fun validError(error: String): Int {
            return when {
                error.contains("There is no user record corresponding to this identifier") -> {
                    R.string.account_not_registered_register_fragment
                }
                error.contains("The email address is badly formatted") -> {
                    R.string.invalid_email_register_fragment
                }
                error.contains("The password is invalid") -> {
                    R.string.invalid_password_register_fragment
                }
                error.contains("The email address is already in use by another account") -> {
                    R.string.email_in_use_register_fragment
                }
                error.contains("Password should be at least 6 characters") -> {
                    R.string.strong_password_register_fragment
                }
                else -> {
                    R.string.error_generic
                }
            }
        }
    }

}