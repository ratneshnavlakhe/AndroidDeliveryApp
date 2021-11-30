package com.example.milkrecordkeeping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.milkrecordkeeping.databinding.AddMilkEntryFragmentBinding

class AddMilkEntryFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: AddMilkEntryFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddMilkEntryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}