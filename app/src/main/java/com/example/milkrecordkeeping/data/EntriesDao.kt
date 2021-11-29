package com.example.milkrecordkeeping.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EntriesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entries: MilkEntries)

    @Query("SELECT * FROM milk_entries ORDER BY created_at ASC")
    fun getEntryList(): Flow<List<MilkEntries>>

    @Query("SELECT * FROM milk_entries WHERE id = :id")
    fun getEntryById(id: Int): Flow<MilkEntries>

    @Query("SELECT * FROM milk_entries WHERE agent_id = :agentId")
    fun getEntriesByAgent(agentId: Int): Flow<List<MilkEntries>>
}