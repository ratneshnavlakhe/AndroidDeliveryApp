package com.example.milkrecordkeeping

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.milkrecordkeeping.AgentListAdapter.AgentViewHolder
import com.example.milkrecordkeeping.data.Agent
import com.example.milkrecordkeeping.databinding.AgentListItemBinding

class AgentListAdapter(private val onItemClicked: (Agent) -> Unit) :
    ListAdapter<Agent, AgentViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Agent>() {
            override fun areItemsTheSame(oldItem: Agent, newItem: Agent): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Agent, newItem: Agent): Boolean {
                return oldItem.agentName == newItem.agentName
            }

        }
    }

    class AgentViewHolder(private var binding: AgentListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(agent: Agent) {
            binding.apply {
                name.text = agent.agentName
                phone.text = agent.agentMobile
                rate.text = agent.milkRate.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentViewHolder {
        return AgentViewHolder(
            AgentListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AgentViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current)
    }

}
