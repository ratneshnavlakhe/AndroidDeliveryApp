package com.example.milkrecordkeeping.di

import androidx.lifecycle.ViewModel
import com.example.milkrecordkeeping.AddMilkmanFragment
import com.example.milkrecordkeeping.DeliveryPersonViewModel
import com.example.milkrecordkeeping.DeliveryPersonViewModelBuilder
import com.example.milkrecordkeeping.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class AddMilkmanModule {
    @ContributesAndroidInjector(
        modules = [
            DeliveryPersonViewModelBuilder::class
        ]
    )
    internal abstract fun addMilkmanFragment(): AddMilkmanFragment

    @Binds
    @IntoMap
    @ViewModelKey(DeliveryPersonViewModel::class)
    internal abstract fun bindViewModel(viewModel: DeliveryPersonViewModel): ViewModel
}
