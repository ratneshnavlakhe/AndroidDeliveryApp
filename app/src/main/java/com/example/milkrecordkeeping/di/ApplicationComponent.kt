package com.example.milkrecordkeeping.di

import android.content.Context
import com.example.milkrecordkeeping.MilkRecordKeepingApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        ListDeliveryPersonModule::class,
        AddMilkmanModule::class,
        ListMilkEntriesModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<MilkRecordKeepingApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}