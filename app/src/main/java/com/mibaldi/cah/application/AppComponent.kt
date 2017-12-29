package com.mibaldi.cah.application

import com.mibaldi.cah.ui.presenters.configuration.ConfigurationComponent
import com.mibaldi.cah.ui.presenters.configuration.ConfigurationModule
import com.mibaldi.cah.ui.presenters.main.MainComponent
import com.mibaldi.cah.ui.presenters.main.MainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: App)
    fun plus(homeModule: MainModule): MainComponent
    fun plus(configurationModule: ConfigurationModule): ConfigurationComponent
}