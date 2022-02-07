package com.example.milkrecordkeeping.di

import androidx.lifecycle.ViewModel
import com.example.milkrecordkeeping.DeliveryPersonViewModel
import com.example.milkrecordkeeping.ListDeliveryPersonFragment
import com.example.milkrecordkeeping.DeliveryPersonViewModelBuilder
import com.example.milkrecordkeeping.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ListDeliveryPersonModule {

    @ContributesAndroidInjector(
        modules = [
            DeliveryPersonViewModelBuilder::class
        ]
    )
    internal abstract fun addListDeliveryPersonFragment(): ListDeliveryPersonFragment

    @Binds
    @IntoMap
    @ViewModelKey(DeliveryPersonViewModel::class)
    internal abstract fun bindViewModel(viewModel: DeliveryPersonViewModel): ViewModel
}
