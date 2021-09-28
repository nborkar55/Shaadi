package com.nikhil.shaadi_demo_app.helper

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.*

object MoshiHelper {

    fun getDefaultInstance(): Moshi {
        return Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter())
                .add(KotlinJsonAdapterFactory())
                .build()
    }

    fun getDefaultBuilder(): Moshi.Builder {
        return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
    }
}
