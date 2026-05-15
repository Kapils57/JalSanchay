package com.example.jalsanchaytracker

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserSetupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSetup(setup: UserSetup)

    @Query("SELECT * FROM user_setup WHERE id = 1")
    fun getSetup(): LiveData<UserSetup?>

    @Query("SELECT * FROM user_setup WHERE id = 1")
    suspend fun getSetupOnce(): UserSetup?
}