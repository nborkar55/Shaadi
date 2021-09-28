package com.nikhil.shaadi_demo_app.retrofit

import com.nikhil.shaadi_demo_app.helper.MoshiHelper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

const val SERVER_URL: String = "https://jioml.jio.com/"

private const val DEFAULT_TIMEOUT_IN_SECONDS = 20L

object RetrofitClient {

    private var apiServices: ApiServices? = null

    val client: ApiServices
        get() {
            return apiServices ?: synchronized(this) {
                apiServices ?: Retrofit.Builder()
                        .baseUrl(SERVER_URL)
                        .addConverterFactory(MoshiConverterFactory.create(MoshiHelper.getDefaultInstance()))
                        .client(
                                OkHttpClient.Builder()
                                        .followRedirects(true)
                                        .followSslRedirects(false)
                                        .retryOnConnectionFailure(true)
                                        .connectTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                                        .readTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                                        .writeTimeout(DEFAULT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                                        .build()
                        )
                        .build()
                        .create(ApiServices::class.java).also { apiServices = it }
            }
        }
}
