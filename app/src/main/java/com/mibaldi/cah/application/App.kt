package com.mibaldi.cah.application
import android.content.Context
import com.mibaldi.cah.application.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication



class App: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}