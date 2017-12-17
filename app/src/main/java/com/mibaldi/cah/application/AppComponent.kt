package com.mibaldi.cah.application

import com.mibaldi.cah.ui.presenters.MainComponent
import com.mibaldi.cah.ui.presenters.MainModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: App)
    fun plus(homeModule: MainModule): MainComponent
}