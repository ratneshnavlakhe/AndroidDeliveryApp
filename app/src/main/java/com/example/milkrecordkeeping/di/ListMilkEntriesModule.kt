package com.example.milkrecordkeeping.di

import androidx.lifecycle.ViewModel
import com.example.milkrecordkeeping.ListMilkEntriesFragment
import com.example.milkrecordkeeping.MilkEntriesViewModel
import com.example.milkrecordkeeping.MilkEntriesViewModelBuilder
import com.example.milkrecordkeeping.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ListMilkEntriesModule {

    @ContributesAndroidInjector(
        modules = [
            MilkEntriesViewModelBuilder::class
        ]
    )
    internal abstract fun listMilkEntriesFragment(): ListMilkEntriesFragment

    @Binds
    @IntoMap
    @ViewModelKey(MilkEntriesViewModel::class)
    internal abstract fun bindViewModel(viewModel: MilkEntriesViewModel): ViewModel
}