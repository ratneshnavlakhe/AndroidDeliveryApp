package com.example.milkrecordkeeping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.milkrecordkeeping.databinding.ListMilkEntriesFragmentBinding

class  ListMilkEntriesFragment : Fragment() {
    private val navigationArgs: ListMilkEntriesFragmentArgs by navArgs()

    private val viewModel: MilkEntriesViewModel by activityViewModels {
        MilkEntriesViewModelFactory(
            (activity?.application as MilkRecordKeepingApplication).database.milkEntriesDao()
        )
    }

    private var _binding: ListMilkEntriesFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListMilkEntriesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val agentId = navigationArgs.agentId
        val adapter = MilkEntriesAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.getMilkEntries(agentId).observe(this.viewLifecycleOwner) { entries ->
            entries.let {
                adapter.submitList(it)
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)

        binding.addMilk.setOnClickListener {
            val action =
                ListMilkEntriesFragmentDirections.actionListMilkEntriesFragmentToAddMilkEntryFragment(
                    navigationArgs.agentId
                )
            this.findNavController().navigate(action)
        }
    }
}