package br.com.hellodev.movieapp.data.repository.user

import android.net.Uri
import br.com.hellodev.movieapp.domain.model.user.User
import br.com.hellodev.movieapp.domain.repository.user.UserRepository
import br.com.hellodev.movieapp.util.FirebaseHelper
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl @Inject constructor(
    firebaseDatabase: FirebaseDatabase,
    firebaseStorage: FirebaseStorage
) : UserRepository {

    private val profileRef = firebaseDatabase.reference
        .child("profile")

    private val profileImageRef = firebaseStorage.reference
        .child("images")
        .child("profiles")
        .child(FirebaseHelper.getUserId())
        .child("image_profile.jpeg")

    override suspend fun update(user: User) {
        return suspendCoroutine { continuation ->
            profileRef
                .child(FirebaseHelper.getUserId())
                .setValue(user)
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

    override suspend fun saveUserImage(uri: Uri): String {
        return suspendCoroutine { continuation ->
            val uploadTask = profileImageRef.putFile(uri)
            uploadTask.addOnProgressListener { taskSnapshot ->
                val progress = (100.0 * taskSnapshot.bytesTransferred) / taskSnapshot.totalByteCount
            }.addOnSuccessListener {
                profileImageRef.downloadUrl.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val downloadUri = task.result.toString()
                        continuation.resumeWith(Result.success(downloadUri))
                    } else {
                        task.exception?.let {
                            continuation.resumeWith(Result.failure(it))
                        }
                    }
                }
            }.addOnFailureListener {
                continuation.resumeWithException(it)
            }
        }
    }

    override suspend fun getUser(): User {
        return suspendCoroutine { continuation ->
            profileRef
                .child(FirebaseHelper.getUserId())
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = task.result?.getValue(User::class.java)
                        if (user != null) {
                            continuation.resumeWith(Result.success(user))
                        } else {
                            continuation.resumeWith(
                                Result.failure(
                                    Exception("User not found")
                                )
                            )
                        }
                    } else {
                        task.exception?.let {
                            continuation.resumeWith(Result.failure(it))
                        }
                    }
                }
                .addOnFailureListener {
                    continuation.resumeWithException(it)
                }
        }
    }

}