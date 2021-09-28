package com.nikhil.shaadi_demo_app.retrofit

import com.nikhil.shaadi_demo_app.model.Profiles
import retrofit2.Response
import retrofit2.http.*

interface ApiServices {

    @GET
    suspend fun getProfiles(@Url url: String): Response<Profiles>
}
