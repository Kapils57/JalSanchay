package com.example.jalsanchaytracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserSetup::class, RainfallEntry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userSetupDao(): UserSetupDao
    abstract fun rainfallDao(): RainfallDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "jalsanchay_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}