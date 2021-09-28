package com.nikhil.shaadi_demo_app.converters

import androidx.room.TypeConverter
import com.nikhil.shaadi_demo_app.helper.MoshiHelper
import com.nikhil.shaadi_demo_app.model.Profiles
import com.squareup.moshi.Types

class TimezoneTypeConverter {

    private val moshi = MoshiHelper.getDefaultInstance()
    private val adapter = moshi.adapter(Profiles.Result.Location.Timezone::class.java)

    @TypeConverter
    fun fromString(value: String?): Profiles.Result.Location.Timezone? {
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(value: Profiles.Result.Location.Timezone?): String? {
        return adapter.toJson(value)
    }
}
