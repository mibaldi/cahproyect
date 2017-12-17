package com.mibaldi.cah.ui.presenters

import com.mibaldi.cah.ui.activities.MainActivity
import dagger.Subcomponent
import javax.inject.Singleton


@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
    fun inject(presenter: MainPresenter )
}