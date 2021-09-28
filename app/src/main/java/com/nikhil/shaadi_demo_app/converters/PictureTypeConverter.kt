package com.nikhil.shaadi_demo_app.converters

import androidx.room.TypeConverter
import com.nikhil.shaadi_demo_app.helper.MoshiHelper
import com.nikhil.shaadi_demo_app.model.Profiles
import com.squareup.moshi.Types

class PictureTypeConverter {

    private val moshi = MoshiHelper.getDefaultInstance()
    private val adapter = moshi.adapter(Profiles.Result.Picture::class.java)

    @TypeConverter
    fun fromString(value: String?): Profiles.Result.Picture? {
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(value: Profiles.Result.Picture?): String? {
        return adapter.toJson(value)
    }
}
