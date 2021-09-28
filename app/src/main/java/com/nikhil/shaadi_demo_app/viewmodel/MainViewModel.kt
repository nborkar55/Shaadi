package com.nikhil.shaadi_demo_app.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nikhil.shaadi_demo_app.Constants
import com.nikhil.shaadi_demo_app.model.Profiles
import com.nikhil.shaadi_demo_app.repository.Repository
import com.nikhil.shaadi_demo_app.retrofit.Result


/***********************************************
 *     Created By Nikhil.Borkar on 26/09/21           *
 ***********************************************/

class MainViewModel : ViewModel() {

    private val repository = Repository()

    suspend fun getProfile() = when (val response = repository.getProfiles(Constants.api_url)) {
        is Result.Success<Profiles> -> {
            response.data
        }
        is Result.Error -> {
            null
        }
    }

    fun getProfilesFromDB():LiveData<List<Profiles.Result>> {
      return repository.getProfilesFormDB()
    }

    suspend fun updateStatus(status: Int,phone:String) =
        repository.updateStatus(status,phone)

}