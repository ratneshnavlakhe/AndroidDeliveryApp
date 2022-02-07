package com.example.milkrecordkeeping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.milkrecordkeeping.databinding.ListMilkEntriesFragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ListMilkEntriesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val navigationArgs: ListMilkEntriesFragmentArgs by navArgs()

    private val viewModel by viewModels<MilkEntriesViewModel> { viewModelFactory }

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