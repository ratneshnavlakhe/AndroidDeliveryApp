package com.example.milkrecordkeeping

import androidx.lifecycle.*
import com.example.milkrecordkeeping.data.Agent
import com.example.milkrecordkeeping.data.AgentDao
import com.example.milkrecordkeeping.util.DateConverter
import kotlinx.coroutines.launch

class DeliveryPersonViewModel(private val agentDao: AgentDao) : ViewModel() {

    val allAgents: LiveData<List<Agent>> = agentDao.getAgents().asLiveData()

    fun getAgent(agentId: Int): LiveData<Agent> {
        return agentDao.getAgentById(agentId)
    }

    fun add(name: String, phone: String, rate: String, address: String) {
        val newAgent = getNewAgentEntry(name, phone, rate, address)
        insertAgent(newAgent)
    }

    fun isEntryValid(name: String, mobile: String, rate: String): Boolean {
        if (name.isBlank() || mobile.isBlank() || rate.isBlank()) {
            return false
        }
        return true
    }

    private fun getNewAgentEntry(name: String, phone: String, rate: String, address: String): Agent {
        return Agent(
            agentName = name,
            agentMobile = phone,
            milkRate = rate.toDouble(),
            createdDate = DateConverter().getDate(),
            agentAddress = address
        )
    }

    private fun insertAgent(agent: Agent) {
        viewModelScope.launch {
            agentDao.insert(agent)
        }
    }

}

class DeliveryPersonViewModelFactory(private val agentDao: AgentDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveryPersonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DeliveryPersonViewModel(agentDao) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class")
    }

}