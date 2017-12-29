package com.mibaldi.cah.ui.presenters.configuration

import com.mibaldi.cah.ui.activities.ConfigurationActivity
import dagger.Subcomponent


@Subcomponent(modules = arrayOf(ConfigurationModule::class))
interface ConfigurationComponent {
    fun inject(activity: ConfigurationActivity)
    fun inject(presenter: ConfigurationPresenter)
}