package com.example.milkrecordkeeping

import com.example.milkrecordkeeping.database.MilkDeliveryDatabase
import com.example.milkrecordkeeping.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MilkRecordKeepingApplication : DaggerApplication() {
    val database: MilkDeliveryDatabase by lazy {
        MilkDeliveryDatabase.getDatabase(this)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.factory().create(applicationContext)
    }
}