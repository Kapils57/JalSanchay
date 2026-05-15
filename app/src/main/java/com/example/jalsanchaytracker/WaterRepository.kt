package com.example.jalsanchaytracker

import android.content.Context

class WaterRepository(context: Context) {

    private val db = AppDatabase.getInstance(context)
    private val setupDao = db.userSetupDao()
    private val rainfallDao = db.rainfallDao()

    suspend fun saveSetup(setup: UserSetup) = setupDao.saveSetup(setup)
    fun getSetup() = setupDao.getSetup()
    suspend fun getSetupOnce() = setupDao.getSetupOnce()

    suspend fun insertRainfall(entry: RainfallEntry) = rainfallDao.insert(entry)
    fun getAllEntries() = rainfallDao.getAllEntries()
    fun getMonthlyTotals() = rainfallDao.getMonthlyTotals()
    fun getTotalLitersSaved() = rainfallDao.getTotalLitersSaved()

    suspend fun deleteRainfall(entry: RainfallEntry) = rainfallDao.delete(entry)
}