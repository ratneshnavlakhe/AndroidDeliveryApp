package com.example.milkrecordkeeping.di

import android.content.Context
import androidx.room.Room
import com.example.milkrecordkeeping.data.AgentDao
import com.example.milkrecordkeeping.data.MIGRATION_1_2
import com.example.milkrecordkeeping.database.MilkDeliveryDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Singleton
    @Provides
    @JvmStatic
    fun providesDatabase(context: Context): MilkDeliveryDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MilkDeliveryDatabase::class.java,
            "milk_delivery_database"
        ).addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @JvmStatic
    fun providesAgentDao(database: MilkDeliveryDatabase): AgentDao {
        return database.agentDao()
    }
}