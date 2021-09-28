package com.nikhil.shaadi_demo_app.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.nikhil.shaadi_demo_app.MyApplication
import com.nikhil.shaadi_demo_app.db.MainDatabase
import com.nikhil.shaadi_demo_app.model.Profiles
import com.nikhil.shaadi_demo_app.retrofit.Result
import com.nikhil.shaadi_demo_app.retrofit.RetrofitClient
import com.nikhil.shaadi_demo_app.safeApiCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.io.IOException
import kotlin.coroutines.CoroutineContext


/***********************************************
 *     Created By Nikhil.Borkar on 26/09/21           *
 ***********************************************/

class Repository : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    suspend fun getProfiles(url: String) = safeApiCall(
        {
            val response = RetrofitClient.client.getProfiles(url)
            if (response.isSuccessful) {
                MyApplication.database?.mainDao()!!.clearAll()
                MyApplication.database?.mainDao()!!.insertResult(response.body()?.results!!)
                Result.Success(response.body()!!)

            } else {
                Result.Error(IOException("${response.code()} ${response.message()}"))
            }
        },
        "error getting profiles through api call"
    )

    fun getProfilesFormDB(): LiveData<List<Profiles.Result>> {
        return MyApplication.database?.mainDao()!!.getAllProfiles()
    }

    suspend fun updateStatus(status: Int,phone:String) {
        MyApplication.database?.mainDao()!!.updateStatus(status,phone)
    }

}