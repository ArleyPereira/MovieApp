package br.com.hellodev.movieapp.data.repository.favorite

import br.com.hellodev.movieapp.domain.model.favorite.FavoriteMovie
import br.com.hellodev.movieapp.domain.repository.movie.FavoriteMovieRepository
import br.com.hellodev.movieapp.util.FirebaseHelper
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class FavoriteMovieRepositoryImpl @Inject constructor(
    firebaseDatabase: FirebaseDatabase
): FavoriteMovieRepository {

    private val favoritesRef = firebaseDatabase.reference
        .child("favorites")

    override suspend fun saveFavorites(favorites: List<FavoriteMovie>) {
        return suspendCoroutine { continuation ->
            favoritesRef
                .child(FirebaseHelper.getUserId())
                .setValue(favorites)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resumeWith(Result.success(Unit))
                    } else {
                        task.exception?.let {
                            continuation.resumeWith(Result.failure(it))
                        }
                    }
                }
        }
    }

}