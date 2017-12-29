package com.mibaldi.cah.ui.presenters.main

import com.mibaldi.cah.ui.activities.MainActivity
import dagger.Subcomponent


@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
    fun inject(presenter: MainPresenter)
}