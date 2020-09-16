package com.app.youtubemusic.di.module

import androidx.lifecycle.ViewModelProvider
import com.app.youtubemusic.factory.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}