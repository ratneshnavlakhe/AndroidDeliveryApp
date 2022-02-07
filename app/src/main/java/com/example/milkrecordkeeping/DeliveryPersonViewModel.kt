package com.example.milkrecordkeeping

import androidx.lifecycle.*
import com.example.milkrecordkeeping.data.Agent
import com.example.milkrecordkeeping.data.AgentDao
import com.example.milkrecordkeeping.util.DateConverter
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.KClass

class DeliveryPersonViewModel @Inject constructor(
    private val agentDao: AgentDao
) : ViewModel() {

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

class DeliveryPersonViewModelFactory @Inject constructor(private val agentDao: AgentDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeliveryPersonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DeliveryPersonViewModel(agentDao) as T
        }

        throw IllegalArgumentException("Unknown viewmodel class")
    }

}

@Module
internal abstract class DeliveryPersonViewModelBuilder {
    @Binds
    internal abstract fun bindViewModelFactory(
        factory: DeliveryPersonViewModelFactory
    ): ViewModelProvider.Factory
}

@Target(
    AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)