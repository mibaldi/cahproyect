package com.mibaldi.cah.application
import com.mibaldi.cah.application.di.DaggerAppComponent
import com.mibaldi.cah.managers.SharedPreferencesManager
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App: DaggerApplication() {

    var prefs: SharedPreferencesManager? = null

    override fun onCreate() {
        prefs = SharedPreferencesManager(applicationContext)
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}