package com.example.jalsanchaytracker

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_setup")
data class UserSetup(
    @PrimaryKey val id: Int = 1,
    val roofAreaSqFt: Float,
    val tankCapacityLitres: Float,
    val runoffCoefficient: Float
)