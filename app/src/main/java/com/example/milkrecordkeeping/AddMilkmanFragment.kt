package com.example.milkrecordkeeping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.milkrecordkeeping.databinding.FragmentAddMilkmanBinding

class AddMilkmanFragment : Fragment() {
    private val viewModel: DeliveryPersonViewModel by activityViewModels {
        DeliveryPersonViewModelFactory(
            (activity?.application as MilkRecordKeepingApplication).database.agentDao()
        )
    }
    private var _binding: FragmentAddMilkmanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddMilkmanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.saveAction.setOnClickListener {
            addNewMilkMan()
        }
    }

    private fun addNewMilkMan() {
        if (isEntryValid()) {
            viewModel.add(
                binding.itemName.text.toString(),
                binding.mobile.text.toString(),
                binding.rate.text.toString(),
                ""
            )
            val action = AddMilkmanFragmentDirections.actionAddMilkmanFragmentToListDeliveryPersonFragment()
            findNavController().navigate(action)
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.itemName.text.toString(),
            binding.mobile.text.toString(),
            binding.rate.text.toString()
        )
    }
}