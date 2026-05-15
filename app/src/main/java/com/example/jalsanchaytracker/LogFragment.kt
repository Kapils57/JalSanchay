package com.example.jalsanchaytracker

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jalsanchaytracker.databinding.FragmentLogBinding

class LogFragment : Fragment() {

    private var _binding: FragmentLogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WaterWealthViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val adapter = RainfallEntryAdapter { entry ->
            // Show confirmation dialog before deleting
            AlertDialog.Builder(requireContext())
                .setTitle("Delete Entry")
                .setMessage("Delete entry for ${entry.date}?")
                .setPositiveButton("Delete") { _, _ ->
                    viewModel.deleteRainfall(entry)
                    Toast.makeText(requireContext(), "Entry deleted", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        binding.rvRecentEntries.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecentEntries.adapter = adapter

        viewModel.allEntries.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.etRainfallMm.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val mm = s.toString().toFloatOrNull()
                val setup = viewModel.setup.value
                if (mm != null && setup != null) {
                    val liters = viewModel.calculateLiters(setup.roofAreaSqFt, mm, setup.runoffCoefficient)
                    binding.tvCalculatedPreview.text = "≈ %.1f litres will be saved".format(liters)
                    binding.tvErrorMessage.visibility = View.GONE
                } else {
                    binding.tvCalculatedPreview.text = ""
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.btnLogRainfall.setOnClickListener {
            val input = binding.etRainfallMm.text.toString()
            viewModel.logRainfall(
                rainfallInput = input,
                onSuccess = {
                    binding.etRainfallMm.text?.clear()
                    binding.tvCalculatedPreview.text = ""
                    Toast.makeText(requireContext(), "Rainfall logged!", Toast.LENGTH_SHORT).show()
                },
                onError = { msg ->
                    binding.tvErrorMessage.text = msg
                    binding.tvErrorMessage.visibility = View.VISIBLE
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
