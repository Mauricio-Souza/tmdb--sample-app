package com.msousa.tmdbredux.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.msousa.tmdbredux.BuildConfig
import com.msousa.tmdbredux.data.remote.OkHttpProvider.okHttpClient
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    @PublishedApi
    internal val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    inline fun <reified T> get(): T {
        return retrofit.create(T::class.java)
    }
}

object OkHttpProvider {

    val okHttpClient : OkHttpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(MainInterceptor())
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}

class MainInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url : HttpUrl = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()

        val request : Request = chain.request()
            .newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}