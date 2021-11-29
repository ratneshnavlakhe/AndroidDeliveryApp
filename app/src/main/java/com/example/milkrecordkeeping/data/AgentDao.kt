package com.example.milkrecordkeeping.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AgentDao {
    @Insert(onConflict = IGNORE)
    suspend fun insert(agent: Agent)

    @Query("SELECT * FROM agent ORDER BY created_at ASC")
    fun getAgents(): Flow<List<Agent>>

    @Query("SELECT * FROM agent WHERE id = :id")
    fun getAgentById(id: Int): Flow<Agent>
}