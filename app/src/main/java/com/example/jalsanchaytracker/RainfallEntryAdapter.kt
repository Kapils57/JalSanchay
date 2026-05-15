package com.example.jalsanchaytracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jalsanchaytracker.databinding.ItemRainfallEntryBinding

class RainfallEntryAdapter(
    private val onDeleteClick: (RainfallEntry) -> Unit
) : ListAdapter<RainfallEntry, RainfallEntryAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(private val binding: ItemRainfallEntryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: RainfallEntry) {
            binding.tvEntryDate.text = entry.date
            binding.tvEntryRainfall.text = "${entry.rainfallMm} mm rainfall"
            binding.tvEntryLiters.text = "%.1f L".format(entry.litersSaved)
            binding.btnDelete.setOnClickListener {
                onDeleteClick(entry)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemRainfallEntryBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<RainfallEntry>() {
            override fun areItemsTheSame(a: RainfallEntry, b: RainfallEntry) = a.id == b.id
            override fun areContentsTheSame(a: RainfallEntry, b: RainfallEntry) = a == b
        }
    }
}
