package com.example.milkrecordkeeping

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.milkrecordkeeping.data.MilkEntries
import com.example.milkrecordkeeping.databinding.MilkEntriesItemBinding

class MilkEntriesAdapter :
    ListAdapter<MilkEntries, MilkEntriesAdapter.MilkEntriesViewHolder>(DiffCallback) {


    class MilkEntriesViewHolder(private var binding: MilkEntriesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(current: MilkEntries) {
            binding.apply {
                rate.text = current.rate.toString()
                quantity.text = current.quantity.toString()
                session.text = current.session.toString()
                date.text = current.createdDate
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MilkEntriesViewHolder {
        return MilkEntriesViewHolder(
            MilkEntriesItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MilkEntriesViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<MilkEntries>() {
            override fun areItemsTheSame(oldItem: MilkEntries, newItem: MilkEntries): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MilkEntries, newItem: MilkEntries): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}