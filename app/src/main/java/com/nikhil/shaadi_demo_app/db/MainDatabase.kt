package com.nikhil.shaadi_demo_app.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nikhil.shaadi_demo_app.converters.*
import com.nikhil.shaadi_demo_app.dao.MainDao
import com.nikhil.shaadi_demo_app.model.Profiles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable


/***********************************************
 *     Created By Nikhil.Borkar on 26/09/21           *
 ***********************************************/
@Database(
    entities = [
        Profiles.Result::class
    ],
    exportSchema = false, version = 1
)
@TypeConverters(
    CoordinatesTypeConverter::class,
    DOBTypeConverter::class,
    IdTypeConverter::class,
    LocationTypeConverter::class,
    LoginTypeConverter::class,
    NameTypeConverter::class,
    PictureTypeConverter::class,
    RegisteredTypeConverter::class,
    StreetTypeConverter::class,
    TimezoneTypeConverter::class
)
abstract class MainDatabase : RoomDatabase(), Serializable {
    abstract fun mainDao(): MainDao

    companion object {
        const val TAG = "MainDatabase"
        private const val DB_NAME = "main-db"

        @Volatile
        private var instance: MainDatabase? = null


        fun getInstance(context: Context): MainDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MainDatabase {
            return Room.databaseBuilder(context, MainDatabase::class.java, DB_NAME)
                .enableMultiInstanceInvalidation()
                .fallbackToDestructiveMigration()
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d(TAG, "db test: MainDatabase oncreate")
                        prePopulateDb()
                    }

                    override fun onDestructiveMigration(db: SupportSQLiteDatabase) {
                        super.onDestructiveMigration(db)
                        prePopulateDb()
                    }
                })
                .build()
        }

        private fun prePopulateDb() {
            CoroutineScope(Dispatchers.Main).launch {
                Log.d(TAG, "db test: onDestructiveMigration loading files")

            }
        }
    }
}