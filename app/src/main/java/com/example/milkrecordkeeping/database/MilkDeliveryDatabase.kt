package com.example.milkrecordkeeping.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.milkrecordkeeping.data.Agent
import com.example.milkrecordkeeping.data.AgentDao
import com.example.milkrecordkeeping.data.EntriesDao
import com.example.milkrecordkeeping.data.MilkEntries

@Database(entities = [Agent::class, MilkEntries::class], version = 1, exportSchema = false)
abstract class MilkDeliveryDatabase : RoomDatabase() {

    abstract fun agentDao(): AgentDao

    abstract fun milkEntriesDao(): EntriesDao

    companion object {
        @Volatile
        private var INSTANCE: MilkDeliveryDatabase? = null

        fun getDatabase(context: Context): MilkDeliveryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    MilkDeliveryDatabase::class.java,
                    "milk_delivery_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}