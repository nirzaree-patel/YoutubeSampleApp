package com.app.youtubemusic.di.component

import android.app.Application
import com.app.youtubemusic.YoutubeApp
import com.app.youtubemusic.di.builder.ActivityBuilder
import com.app.youtubemusic.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        ApiModule::class,
        DatabaseModule::class,
        AppModule::class,
        PlaylistDataSourceFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<YoutubeApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun create(application: Application): Builder

        fun build(): AppComponent
    }


    override fun inject(instance: YoutubeApp)
}