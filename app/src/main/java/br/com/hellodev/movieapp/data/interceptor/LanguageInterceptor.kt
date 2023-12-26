package br.com.hellodev.movieapp.data.interceptor

import br.com.hellodev.movieapp.util.Constants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response

class LanguageInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalHttpUrl: HttpUrl = originalRequest.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(LANGUAGE, Constants.Movie.LANGUAGE)
            .build()

        val requestBuilder = originalRequest.newBuilder().url(url)
        val request = requestBuilder.build()

        return chain.proceed(request)
    }

    companion object {
        private const val LANGUAGE = "language"
    }

}