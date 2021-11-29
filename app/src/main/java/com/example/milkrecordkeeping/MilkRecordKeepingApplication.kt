package com.example.milkrecordkeeping

import android.app.Application
import com.example.milkrecordkeeping.database.MilkDeliveryDatabase

class MilkRecordKeepingApplication : Application() {
    val database: MilkDeliveryDatabase by lazy {
        MilkDeliveryDatabase.getDatabase(this)
    }
}