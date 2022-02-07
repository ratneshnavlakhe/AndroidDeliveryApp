package com.example.milkrecordkeeping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.milkrecordkeeping.databinding.ListDeliveryPersonFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListDeliveryPersonFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DeliveryPersonViewModel> { viewModelFactory }

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
        val adapter = AgentListAdapter {
            val action =
                ListDeliveryPersonFragmentDirections.actionListDeliveryPersonFragmentToListMilkEntriesFragment(
                    it
                )
            this.findNavController().navigate(action)
        }
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