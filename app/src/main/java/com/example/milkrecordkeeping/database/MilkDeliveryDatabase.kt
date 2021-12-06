package com.example.milkrecordkeeping.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.milkrecordkeeping.data.*

@Database(entities = [Agent::class, MilkEntries::class], version = 2, exportSchema = false)
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
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}