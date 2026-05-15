package com.example.jalsanchaytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jalsanchaytracker.databinding.FragmentTipsBinding

class TipsFragment : Fragment() {

    private var _binding: FragmentTipsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val tips = listOf(
            Tip("Clean your roof before monsoon",
                "Dust and debris reduce runoff quality and clog filters. Sweep the catchment area before the first rain."),
            Tip("Install a first-flush diverter",
                "The first 2mm of rainfall washes pollutants. A diverter discards this and routes clean water to your tank."),
            Tip("Understand your Runoff Coefficient",
                "Smooth RCC roofs yield 80% of rainfall. Rough or green roofs absorb more — factor this into your calculations."),
            Tip("Use drip irrigation for garden",
                "Drip systems use 30–50% less water than sprinklers. Use your harvested water efficiently for plants."),
            Tip("Check for tank leakages monthly",
                "A small crack can lose hundreds of litres per day. Inspect joints and the base every month."),
            Tip("Calculate your catchment area accurately",
                "Measure only the roof area that drains into your tank. Exclude areas that drain to the street."),
            Tip("Track rainfall patterns",
                "Log every rain event, even light ones. Monthly totals help you plan water reuse and predict lean seasons.")
        )

        val adapter = TipsAdapter(tips)
        binding.rvTips.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTips.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}