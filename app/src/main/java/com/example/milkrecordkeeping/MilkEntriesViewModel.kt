package com.example.milkrecordkeeping

import androidx.lifecycle.*
import com.example.milkrecordkeeping.data.EntriesDao
import com.example.milkrecordkeeping.data.MilkEntries
import com.example.milkrecordkeeping.util.DateConverter
import kotlinx.coroutines.launch

class MilkEntriesViewModel(private val entriesDao: EntriesDao) : ViewModel() {
    fun getMilkEntries(agentId: Int): LiveData<List<MilkEntries>> {
        return entriesDao.getEntriesByAgent(agentId).asLiveData()
    }

    fun isEntryValid(rate: String, quantity: String, date: String): Boolean {
        if (rate.isBlank() || quantity.isBlank() || date.isBlank()) {
            return false
        }
        return true
    }

    fun add(
        rate: String,
        quantity: String,
        checkedRadioButtonId: String,
        agentId: Int,
        dateOfDelivery: String
    ) {
        val newEntry = getMilkEntries(rate, quantity, agentId, checkedRadioButtonId, dateOfDelivery)
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
        checkedRadioButtonId: String,
        dateOfDelivery: String
    ) = MilkEntries(
        rate = rate.toDouble(),
        quantity = quantity.toDouble(),
        session = checkedRadioButtonId,
        agentId = agentId,
        createdDate = DateConverter().getDate(),
        deliveryDate = dateOfDelivery
    )
}

class MilkEntriesViewModelFactory(private val entriesDao: EntriesDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MilkEntriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MilkEntriesViewModel(entriesDao) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class")
    }

}