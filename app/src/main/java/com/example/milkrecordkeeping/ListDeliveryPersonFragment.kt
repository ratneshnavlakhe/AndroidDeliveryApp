package com.example.milkrecordkeeping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.milkrecordkeeping.databinding.ListDeliveryPersonFragmentBinding

class ListDeliveryPersonFragment : Fragment() {

    private val viewModel: DeliveryPersonViewModel by activityViewModels {
        DeliveryPersonViewModelFactory(
            (activity?.application as MilkRecordKeepingApplication).database.agentDao()
        )
    }

    private var _binding: ListDeliveryPersonFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListDeliveryPersonFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = AgentListAdapter {}
        binding.recyclerView.adapter = adapter

        viewModel.allAgents.observe(this.viewLifecycleOwner) { agents ->
            agents.let {
                adapter.submitList(it)
            }
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.floatingActionButton.setOnClickListener {
            val action =
                ListDeliveryPersonFragmentDirections.actionListDeliveryPersonFragmentToAddMilkmanFragment()
            this.findNavController().navigate(action)
        }
    }

}