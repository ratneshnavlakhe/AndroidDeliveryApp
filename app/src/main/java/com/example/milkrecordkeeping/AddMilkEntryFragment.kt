package com.example.milkrecordkeeping

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.milkrecordkeeping.databinding.AddMilkEntryFragmentBinding
import dagger.android.support.DaggerFragment
import java.util.*
import javax.inject.Inject

class AddMilkEntryFragment : DaggerFragment() {

    @Inject
    lateinit var milkEntriesViewModelFactory: MilkEntriesViewModelFactory

    @Inject
    lateinit var deliveryPersonViewModelFactory: DeliveryPersonViewModelFactory

    private val navigationArgs: AddMilkEntryFragmentArgs by navArgs()

    private val viewModel by viewModels<MilkEntriesViewModel> { milkEntriesViewModelFactory }

    private val deliveryPersonViewModel by viewModels<DeliveryPersonViewModel> { deliveryPersonViewModelFactory }

    private var _binding: AddMilkEntryFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var datePickerDialog: DatePickerDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddMilkEntryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deliveryPersonViewModel.getAgent(navigationArgs.agentId)
            .observe(this.viewLifecycleOwner) { agent ->
                binding.rate.text =
                    Editable.Factory.getInstance().newEditable(agent.milkRate.toString())
            }

        binding.deliveryDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val mYear = calendar[Calendar.YEAR]
            val mMonth = calendar[Calendar.MONTH]
            val mDay = calendar[Calendar.DAY_OF_MONTH]
            datePickerDialog = context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    { _, year, monthOfYear, dayOfMonth ->
                        binding.deliveryDate.setText(
                            getString(R.string.date, dayOfMonth, monthOfYear + 1, year)
                        )
                    }, mYear, mMonth, mDay
                )
            }!!
            datePickerDialog.show()
        }

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
                navigationArgs.agentId,
                binding.deliveryDate.text.toString()
            )

            val action =
                AddMilkEntryFragmentDirections.actionAddMilkEntryFragmentToListMilkEntriesFragment(
                    navigationArgs.agentId
                )
            findNavController().navigate(action)
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.rate.text.toString(),
            binding.quantity.text.toString(),
            binding.deliveryDate.text.toString()
        )
    }
}