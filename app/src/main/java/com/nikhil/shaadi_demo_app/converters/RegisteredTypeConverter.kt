package com.nikhil.shaadi_demo_app.converters

import androidx.room.TypeConverter
import com.nikhil.shaadi_demo_app.helper.MoshiHelper
import com.nikhil.shaadi_demo_app.model.Profiles
import com.squareup.moshi.Types

class RegisteredTypeConverter {

    private val moshi = MoshiHelper.getDefaultInstance()
    private val adapter = moshi.adapter(Profiles.Result.Registered::class.java)

    @TypeConverter
    fun fromString(value: String?): Profiles.Result.Registered? {
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(value: Profiles.Result.Registered?): String? {
        return adapter.toJson(value)
    }
}
