package br.com.hellodev.movieapp.data.mapper

import br.com.hellodev.movieapp.data.local.entity.MovieEntity
import br.com.hellodev.movieapp.data.model.AuthorDetailsResponse
import br.com.hellodev.movieapp.data.model.CountryResponse
import br.com.hellodev.movieapp.data.model.CreditResponse
import br.com.hellodev.movieapp.data.model.GenreResponse
import br.com.hellodev.movieapp.data.model.MovieResponse
import br.com.hellodev.movieapp.data.model.MovieReviewResponse
import br.com.hellodev.movieapp.data.model.PersonResponse
import br.com.hellodev.movieapp.domain.model.AuthorDetails
import br.com.hellodev.movieapp.domain.model.Country
import br.com.hellodev.movieapp.domain.model.Credit
import br.com.hellodev.movieapp.domain.model.Genre
import br.com.hellodev.movieapp.domain.model.Movie
import br.com.hellodev.movieapp.domain.model.MovieReview
import br.com.hellodev.movieapp.domain.model.Person
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
        genres = genres?.map { it.toDomain() },
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
        voteCount = voteCount,
        productionCountries = productionCountries?.map { it.toDomain() },
        runtime = runtime
    )
}

fun Genre.toPresentation(): GenrePresentation {
    return GenrePresentation(
        id = id,
        name = name,
        movies = emptyList()
    )
}

fun CountryResponse.toDomain(): Country {
    return Country(
        name = name
    )
}

fun PersonResponse.toDomain(): Person {
    return Person(
        adult = adult,
        gender = gender,
        id = id,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath,
        castId = castId,
        character = character,
        creditId = creditId,
        order = order
    )
}

fun CreditResponse.toDomain(): Credit {
    return Credit(
        cast = cast?.map { it.toDomain() }
    )
}

fun AuthorDetailsResponse.toDomain(): AuthorDetails {
    return AuthorDetails(
        name = name,
        username = username,
        avatarPath = avatarPath,
        rating = rating
    )
}

fun MovieReviewResponse.toDomain(): MovieReview {
    return MovieReview(
        author = author,
        authorDetails = authorDetailsResponse?.toDomain(),
        content = content,
        createdAt = createdAt,
        id = id,
        updatedAt = updatedAt,
        url = url
    )
}

fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        poster = posterPath,
        runtime = runtime,
        insertion = System.currentTimeMillis()
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        posterPath = poster,
        runtime = runtime
    )
}