package com.example.jalsanchaytracker

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RainfallDao {

    @Insert
    suspend fun insert(entry: RainfallEntry)

    @Query("SELECT * FROM rainfall_entry ORDER BY date DESC")
    fun getAllEntries(): LiveData<List<RainfallEntry>>

    @Query("SELECT * FROM rainfall_entry WHERE date = :date LIMIT 1")
    suspend fun getEntryForDate(date: String): RainfallEntry?

    @Query("""
        SELECT strftime('%Y-%m', date) AS month, SUM(litersSaved) AS total
        FROM rainfall_entry
        GROUP BY month
        ORDER BY month DESC
    """)
    fun getMonthlyTotals(): LiveData<List<MonthlyTotal>>

    @Query("SELECT SUM(litersSaved) FROM rainfall_entry")
    fun getTotalLitersSaved(): LiveData<Float?>

    @Delete
    suspend fun delete(entry: RainfallEntry)
}