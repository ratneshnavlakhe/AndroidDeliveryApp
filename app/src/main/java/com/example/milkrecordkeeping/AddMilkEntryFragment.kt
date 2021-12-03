package com.example.milkrecordkeeping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.milkrecordkeeping.databinding.AddMilkEntryFragmentBinding

class AddMilkEntryFragment : Fragment() {

    private val navigationArgs: AddMilkEntryFragmentArgs by navArgs()

    private val viewModel: MilkEntriesViewModel by activityViewModels {
        MilkEntriesViewModelFactory(
            (activity?.application as MilkRecordKeepingApplication).database.milkEntriesDao(),
            navigationArgs.agentId
        )
    }

    private var _binding: AddMilkEntryFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddMilkEntryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveAction.setOnClickListener {
            addMilkEntry()
        }
    }

    private fun addMilkEntry() {
        val selectedId: Int = binding.session.checkedRadioButtonId
        val radioButton = binding.session.findViewById(selectedId) as RadioButton

        if (isEntryValid()) {
            viewModel.add(
                binding.rate.text.toString(),
                binding.quantity.text.toString(),
                radioButton.text.toString(),
                navigationArgs.agentId
            )
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.rate.text.toString(),
            binding.quantity.text.toString()
        )
    }
}