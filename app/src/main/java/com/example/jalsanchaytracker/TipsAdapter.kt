package com.example.jalsanchaytracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jalsanchaytracker.databinding.ItemTipBinding

class TipsAdapter(private val tips: List<Tip>) : RecyclerView.Adapter<TipsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemTipBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tip: Tip, position: Int) {
            binding.tvTipNumber.text = "${position + 1}"
            binding.tvTipTitle.text = tip.title
            binding.tvTipBody.text = tip.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemTipBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(tips[position], position)

    override fun getItemCount() = tips.size
}