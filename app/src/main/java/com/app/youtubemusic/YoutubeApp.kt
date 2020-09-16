package com.app.youtubemusic

import android.app.Application
import com.app.youtubemusic.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class YoutubeApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().create(this).build().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector
}