package com.example.jalsanchaytracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class WaterWealthViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = WaterRepository(application)

    val setup = repo.getSetup()
    val allEntries = repo.getAllEntries()
    val monthlyTotals = repo.getMonthlyTotals()
    val totalLitersSaved = repo.getTotalLitersSaved()

    fun saveSetup(roofAreaSqFt: Float, tankCapacityLitres: Float, runoffCoefficient: Float) {
        viewModelScope.launch {
            repo.saveSetup(
                UserSetup(
                    roofAreaSqFt = roofAreaSqFt,
                    tankCapacityLitres = tankCapacityLitres,
                    runoffCoefficient = runoffCoefficient
                )
            )
        }
    }

    fun calculateLiters(roofAreaSqFt: Float, rainfallMm: Float, runoffCoefficient: Float): Float {
        return roofAreaSqFt * rainfallMm * 0.0929f * runoffCoefficient
    }

    fun calculateImpactDays(totalLitres: Float): Float {
        return totalLitres / 135f
    }

    fun calculateTankFillPercent(litersSaved: Float, tankCapacityLitres: Float): Int {
        return ((litersSaved / tankCapacityLitres) * 100).toInt().coerceAtMost(100)
    }

    fun logRainfall(rainfallInput: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            val mm = rainfallInput.trim().toFloatOrNull()
            if (mm == null) {
                onError("Please enter a valid number")
                return@launch
            }
            if (mm < 0 || mm > 500) {
                onError("Rainfall must be between 0 and 500 mm")
                return@launch
            }
            val currentSetup = repo.getSetupOnce()
            if (currentSetup == null) {
                onError("Please complete setup first")
                return@launch
            }
            val liters = calculateLiters(
                currentSetup.roofAreaSqFt,
                mm,
                currentSetup.runoffCoefficient
            )
            val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            repo.insertRainfall(
                RainfallEntry(
                    date = today,
                    rainfallMm = mm,
                    litersSaved = liters
                )
            )
            onSuccess()
        }
    }

    fun deleteRainfall(entry: RainfallEntry) {
        viewModelScope.launch {
            repo.deleteRainfall(entry)
        }
    }
}