package com.app.youtubemusic.di.module

import androidx.lifecycle.ViewModel
import com.app.youtubemusic.ui.main.MainViewModel
import com.app.youtubemusic.utils.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}