package com.example.jalsanchaytracker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rainfall_entry")
data class RainfallEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String,
    val rainfallMm: Float,
    val litersSaved: Float
)