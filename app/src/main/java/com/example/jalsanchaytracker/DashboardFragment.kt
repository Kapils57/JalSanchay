package com.example.jalsanchaytracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.jalsanchaytracker.databinding.FragmentDashboardBinding
import java.text.SimpleDateFormat
import java.util.*

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WaterWealthViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        viewModel.totalLitersSaved.observe(viewLifecycleOwner) { total ->
            val safeTotal = total ?: 0f
            binding.tvTotalSavings.text = "%.1f L".format(safeTotal)

            viewModel.setup.value?.let { setup ->
                val fillPct = viewModel.calculateTankFillPercent(safeTotal, setup.tankCapacityLitres)
                animateTankFill(fillPct)
                binding.tvTankPercent.text = "$fillPct%"
                val impactDays = viewModel.calculateImpactDays(safeTotal)
                binding.tvImpactScore.text = "%.1f household water days".format(impactDays)
            }
        }

        viewModel.allEntries.observe(viewLifecycleOwner) { entries ->
            val todayEntry = entries.firstOrNull { it.date == today }
            binding.tvLitersToday.text = if (todayEntry != null)
                "%.1f L".format(todayEntry.litersSaved) else "0 L"
        }

        viewModel.setup.observe(viewLifecycleOwner) { setup ->
            val total = viewModel.totalLitersSaved.value ?: 0f
            if (setup != null) {
                val fillPct = viewModel.calculateTankFillPercent(total, setup.tankCapacityLitres)
                animateTankFill(fillPct)
                binding.tvTankPercent.text = "$fillPct%"
            }
        }

        binding.btnQuickLog.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, LogFragment())
                .commit()
        }
    }

    private fun animateTankFill(targetPercent: Int) {
        val tankContainer = binding.tankContainer
        tankContainer.post {
            val maxHeight = tankContainer.height
            val targetHeight = (maxHeight * targetPercent / 100)
            val currentHeight = binding.viewTankFill.layoutParams.height.takeIf { it > 0 } ?: 0

            val anim = object : Animation() {
                override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                    val h = currentHeight + ((targetHeight - currentHeight) * interpolatedTime).toInt()
                    binding.viewTankFill.layoutParams.height = h
                    binding.viewTankFill.requestLayout()
                }
                override fun willChangeBounds() = true
            }
            anim.duration = 800
            binding.viewTankFill.startAnimation(anim)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}