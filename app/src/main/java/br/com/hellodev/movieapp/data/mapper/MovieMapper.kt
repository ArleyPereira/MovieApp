package br.com.hellodev.movieapp.data.mapper

import br.com.hellodev.movieapp.data.model.GenreResponse
import br.com.hellodev.movieapp.data.model.MovieResponse
import br.com.hellodev.movieapp.domain.model.Genre
import br.com.hellodev.movieapp.domain.model.Movie
import br.com.hellodev.movieapp.presenter.model.GenrePresentation

fun GenreResponse.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun MovieResponse.toDomain(): Movie {
    return Movie(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun Genre.toPresentation(): GenrePresentation {
    return GenrePresentation(
        id = id,
        name = name,
        movies = emptyList()
    )
}