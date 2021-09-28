package com.nikhil.shaadi_demo_app

import android.app.Application
import com.facebook.stetho.Stetho
import com.nikhil.shaadi_demo_app.db.MainDatabase


/***********************************************
 *     Created By Nikhil.Borkar on 27/09/21           *
 ***********************************************/

class MyApplication:Application() {
    companion object {
        var database: MainDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        database =  MainDatabase.getInstance(this)


    }
}