package com.hodinv.spacex.repository.network

import com.hodinv.spacex.repository.BuildConfig
import com.hodinv.spacex.repository.network.api.RocketsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceImpl(
    urlProvider: UrlProvider
) : NetworkService {
    override val rocketsService: RocketsService
        get() = commonApi.create(RocketsService::class.java)

    private val commonApi: Retrofit

    init {


        val httpClient = OkHttpClient.Builder()

        addLogging(httpClient)

        val client = httpClient.build()
        commonApi = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(urlProvider.baseUrl)
            .build()

    }


    private fun addLogging(httpClient: OkHttpClient.Builder) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        httpClient.addInterceptor(loggingInterceptor)
    }


}