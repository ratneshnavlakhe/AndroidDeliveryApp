package com.example.milkrecordkeeping.di

import androidx.lifecycle.ViewModel
import com.example.milkrecordkeeping.*
import com.example.milkrecordkeeping.DeliveryPersonViewModelBuilder
import com.example.milkrecordkeeping.MilkEntriesViewModelBuilder
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AddMilkEntryModule {

    @ContributesAndroidInjector(
        modules = [
            MilkEntriesViewModelBuilder::class,
            DeliveryPersonViewModelBuilder::class
        ]
    )
    internal abstract fun addMilkEntryFragment(): AddMilkEntryFragment

    @Binds
    @IntoMap
    @ViewModelKey(MilkEntriesViewModel::class)
    internal abstract fun bindViewModel(viewModel: MilkEntriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeliveryPersonViewModel::class)
    internal abstract fun bindDeliveryViewModel(viewModel: DeliveryPersonViewModel): ViewModel
}