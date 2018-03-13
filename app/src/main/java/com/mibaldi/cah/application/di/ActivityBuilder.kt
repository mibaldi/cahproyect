package com.mibaldi.cah.application.di


import com.mibaldi.cah.ui.activities.*
import dagger.Module
import com.mibaldi.cah.ui.fragments.di.GameFragmentsProvider
import com.mibaldi.cah.ui.presenters.configuration.ConfigurationModule
import com.mibaldi.cah.ui.presenters.game.GameModule
import com.mibaldi.cah.ui.presenters.main.MainModule
import com.mibaldi.cah.ui.presenters.newGame.NewGameModule
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(MainModule::class))
    abstract fun MainActivity(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(ConfigurationModule::class))
    abstract fun ConfigurationActivity(): ConfigurationActivity

    @ContributesAndroidInjector(modules = arrayOf(NewGameModule::class))
    abstract fun NewGameActivity(): NewGameActivity

    @ContributesAndroidInjector(modules = arrayOf(
            GameModule::class,
            GameFragmentsProvider::class
    ))
    abstract fun GameActivity(): GameActivity
}