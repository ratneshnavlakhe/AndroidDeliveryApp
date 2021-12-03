package com.example.milkrecordkeeping

import androidx.lifecycle.*
import com.example.milkrecordkeeping.data.EntriesDao
import com.example.milkrecordkeeping.data.MilkEntries
import com.example.milkrecordkeeping.util.DateConverter
import kotlinx.coroutines.launch

class MilkEntriesViewModel(private val entriesDao: EntriesDao, agentId: Int) : ViewModel() {
    val allEntries: LiveData<List<MilkEntries>> = entriesDao.getEntriesByAgent(agentId).asLiveData()

    fun isEntryValid(rate: String, quantity: String): Boolean {
        if (rate.isBlank() || quantity.isBlank()) {
            return false
        }
        return true
    }

    fun add(rate: String, quantity: String, checkedRadioButtonId: String, agentId: Int) {
        val newEntry = getMilkEntries(rate, quantity, agentId, checkedRadioButtonId)
        insertEntry(newEntry)
    }

    private fun insertEntry(newEntry: MilkEntries) {
        viewModelScope.launch {
            entriesDao.insert(newEntry)
        }
    }


    private fun getMilkEntries(
        rate: String,
        quantity: String,
        agentId: Int,
        checkedRadioButtonId: String
    ) = MilkEntries(
        rate = rate.toDouble(),
        quantity = quantity.toInt(),
        session = checkedRadioButtonId.toInt(),
        agentId = agentId,
        createdDate = DateConverter().getDate()
    )
}

class MilkEntriesViewModelFactory(private val entriesDao: EntriesDao, private val agentId: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MilkEntriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MilkEntriesViewModel(entriesDao, agentId) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class")
    }

}