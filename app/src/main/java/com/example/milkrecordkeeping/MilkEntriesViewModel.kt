package com.example.milkrecordkeeping

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.milkrecordkeeping.data.EntriesDao
import com.example.milkrecordkeeping.data.MilkEntries
import java.lang.IllegalArgumentException

class MilkEntriesViewModel(private val entriesDao: EntriesDao, agentId: Int) : ViewModel() {
    val allEntries: LiveData<List<MilkEntries>> = entriesDao.getEntriesByAgent(agentId).asLiveData()


}

class MilkEntriesViewModelFactory(private val entriesDao: EntriesDao, private val agentId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MilkEntriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MilkEntriesViewModel(entriesDao, agentId) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class")
    }

}