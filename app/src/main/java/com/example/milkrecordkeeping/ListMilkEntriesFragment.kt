package com.example.milkrecordkeeping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.milkrecordkeeping.databinding.ListMilkEntriesFragmentBinding

class  ListMilkEntriesFragment : Fragment() {
    private val navigationArgs: ListMilkEntriesFragmentArgs by navArgs()

    private val viewModel: MilkEntriesViewModel by activityViewModels {
        MilkEntriesViewModelFactory(
            (activity?.application as MilkRecordKeepingApplication).database.milkEntriesDao(),
            1
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
}