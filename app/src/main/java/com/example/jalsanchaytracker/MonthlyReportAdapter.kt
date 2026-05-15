package com.example.jalsanchaytracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jalsanchaytracker.databinding.ItemMonthlyReportBinding

class MonthlyReportAdapter(private val viewModel: WaterWealthViewModel) :
    ListAdapter<MonthlyTotal, MonthlyReportAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(private val binding: ItemMonthlyReportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MonthlyTotal) {
            binding.tvMonth.text = item.month
            binding.tvMonthlyLiters.text = "%.1f L".format(item.total)
            val days = viewModel.calculateImpactDays(item.total)
            binding.tvMonthlyImpact.text = "= %.1f household water days".format(days)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemMonthlyReportBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<MonthlyTotal>() {
            override fun areItemsTheSame(a: MonthlyTotal, b: MonthlyTotal) = a.month == b.month
            override fun areContentsTheSame(a: MonthlyTotal, b: MonthlyTotal) = a == b
        }
    }
}