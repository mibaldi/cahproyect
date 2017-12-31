package com.mibaldi.cah.application.di


import com.mibaldi.cah.ui.activities.ConfigurationActivity
import dagger.Module
import com.mibaldi.cah.ui.activities.MainActivity
import com.mibaldi.cah.ui.presenters.configuration.ConfigurationModule
import com.mibaldi.cah.ui.presenters.main.MainModule
import com.mibaldi.cah.ui.presenters.main.MainPresenter
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun MainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(ConfigurationModule::class))
    abstract fun ConfigurationActivity(): ConfigurationActivity
}