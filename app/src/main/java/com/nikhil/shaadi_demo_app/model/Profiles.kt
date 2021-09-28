package com.nikhil.shaadi_demo_app.model


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

data class Profiles(
    @Json(name = "info")
    val info: Info?,
    @Json(name = "results")
    val results: List<Result>
) {
    data class Info(
        @Json(name = "page")
        val page: Int?,
        @Json(name = "results")
        val results: Int?,
        @Json(name = "seed")
        val seed: String?,
        @Json(name = "version")
        val version: String?
    )
    @Entity
    data class Result(
        @Json(name = "cell")
        val cell: String?,
        @Json(name = "dob")
        val dob: Dob?=null,
        @Json(name = "email")
        val email: String?,
        @Json(name = "gender")
        val gender: String?,
        @Json(name = "id")
        val id: Id,
        @Json(name = "location")
        val location: Location,
        @Json(name = "login")
        val login: Login?=null,
        @Json(name = "name")
        val name: Name?=null,
        @Json(name = "nat")
        val nat: String?,
        @Json(name = "phone")
        val phone: String?,
        @Json(name = "picture")
        val picture: Picture?=null,
        @Json(name = "registered")
        val registered: Registered?=null,
        val status: Int=0
    ) {
        @PrimaryKey(autoGenerate = true)
        var rowId = 0

        data class Dob(
            @Json(name = "age")
            val age: Int?,
            @Json(name = "date")
            val date: String?
        )

        data class Id(
            @Json(name = "name")
            val name: String?,
            @Json(name = "value")
            val value: String?
        )

        data class Location(
            @Json(name = "city")
            val city: String?,
            @Json(name = "coordinates")
            val coordinates: Coordinates?=null,
            @Json(name = "country")
            val country: String?,

//            @Json(name = "postcode")
//            @Ignore
//            val postcode: Int?,
            @Json(name = "state")
            val state: String?,
            @Json(name = "street")
            val street: Street?=null,
            @Json(name = "timezone")
            val timezone: Timezone?=null
        ) {
            data class Coordinates(
                @Json(name = "latitude")
                val latitude: String?,
                @Json(name = "longitude")
                val longitude: String?
            )

            data class Street(
                @Json(name = "name")
                val name: String?,
                @Json(name = "number")
                val number: Int?
            )

            data class Timezone(
                @Json(name = "description")
                val description: String?,
                @Json(name = "offset")
                val offset: String?
            )
        }

        data class Login(
            @Json(name = "md5")
            val md5: String?,
            @Json(name = "password")
            val password: String?,
            @Json(name = "salt")
            val salt: String?,
            @Json(name = "sha1")
            val sha1: String?,
            @Json(name = "sha256")
            val sha256: String?,
            @Json(name = "username")
            val username: String?,
            @Json(name = "uuid")
            val uuid: String?
        )

        data class Name(
            @Json(name = "first")
            val first: String?,
            @Json(name = "last")
            val last: String?,
            @Json(name = "title")
            val title: String?
        )

        data class Picture(
            @Json(name = "large")
            val large: String?,
            @Json(name = "medium")
            val medium: String?,
            @Json(name = "thumbnail")
            val thumbnail: String?
        )

        data class Registered(
            @Json(name = "age")
            val age: Int?,
            @Json(name = "date")
            val date: String?
        )
    }
}