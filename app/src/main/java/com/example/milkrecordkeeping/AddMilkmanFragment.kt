package com.example.milkrecordkeeping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.milkrecordkeeping.databinding.FragmentAddMilkmanBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class AddMilkmanFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<DeliveryPersonViewModel> { viewModelFactory }
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