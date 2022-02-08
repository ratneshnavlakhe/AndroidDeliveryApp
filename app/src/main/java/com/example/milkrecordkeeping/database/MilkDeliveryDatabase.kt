package com.example.milkrecordkeeping.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.milkrecordkeeping.data.Agent
import com.example.milkrecordkeeping.data.AgentDao
import com.example.milkrecordkeeping.data.EntriesDao
import com.example.milkrecordkeeping.data.MilkEntries

@Database(entities = [Agent::class, MilkEntries::class], version = 2, exportSchema = false)
abstract class MilkDeliveryDatabase : RoomDatabase() {

    abstract fun agentDao(): AgentDao

    abstract fun milkEntriesDao(): EntriesDao
}