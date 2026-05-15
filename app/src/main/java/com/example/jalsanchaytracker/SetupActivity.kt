package com.example.jalsanchaytracker

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.jalsanchaytracker.databinding.ActivitySetupBinding

class SetupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySetupBinding
    private val viewModel: WaterWealthViewModel by viewModels()

    private val roofTypes = listOf(
        "RCC flat roof (0.80)",
        "Tiled / sloped roof (0.75)",
        "Rough concrete (0.60)",
        "Gravel / terrace garden (0.50)"
    )
    private val runoffValues = listOf(0.80f, 0.75f, 0.60f, 0.50f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roofTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerRoofType.adapter = adapter

        binding.btnSaveSetup.setOnClickListener {
            val areaStr = binding.etRoofArea.text.toString().trim()
            val tankStr = binding.etTankCapacity.text.toString().trim()

            val area = areaStr.toFloatOrNull()
            val tank = tankStr.toFloatOrNull()

            if (area == null || area <= 0) {
                Toast.makeText(this, "Enter a valid roof area", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (tank == null || tank <= 0) {
                Toast.makeText(this, "Enter a valid tank capacity", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedRunoff = runoffValues[binding.spinnerRoofType.selectedItemPosition]
            viewModel.saveSetup(area, tank, selectedRunoff)

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}