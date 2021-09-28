package com.nikhil.shaadi_demo_app.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikhil.shaadi_demo_app.model.Profiles

@Dao
abstract class MainDao {

    @Query("select * from Result")
    abstract fun getAllProfiles(): LiveData<List<Profiles.Result>>

    @Query("update Result set status=:status where phone=:phone")
    abstract suspend fun updateStatus(status: Int,phone:String)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertResult(result:List<Profiles.Result>)

    @Query("delete from Result")
    abstract suspend fun clearAll()

}
