package br.com.hellodev.movieapp.di

import br.com.hellodev.movieapp.data.local.repository.MovieLocalRepositoryImpl
import br.com.hellodev.movieapp.data.repository.auth.FirebaseAuthenticationImpl
import br.com.hellodev.movieapp.data.repository.movie.MovieDetailsRepositoryImpl
import br.com.hellodev.movieapp.data.repository.movie.MovieRepositoryImpl
import br.com.hellodev.movieapp.data.repository.user.UserRepositoryImpl
import br.com.hellodev.movieapp.domain.local.repository.MovieLocalRepository
import br.com.hellodev.movieapp.domain.repository.auth.FirebaseAuthentication
import br.com.hellodev.movieapp.domain.repository.movie.MovieDetailsRepository
import br.com.hellodev.movieapp.domain.repository.movie.MovieRepository
import br.com.hellodev.movieapp.domain.repository.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsFirebaseAuthenticationImpl(
        firebaseAuthenticationImpl: FirebaseAuthenticationImpl
    ): FirebaseAuthentication

    @Binds
    abstract fun bindsMovieRepositoryImpl(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    abstract fun bindsMovieDetailsRepositoryImpl(
        movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl
    ): MovieDetailsRepository

    @Binds
    abstract fun bindsMovieLocalRepositoryImpl(
        movieLocalRepositoryImpl: MovieLocalRepositoryImpl
    ): MovieLocalRepository

    @Binds
    abstract fun bindsUserRepositoryImpl(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

}