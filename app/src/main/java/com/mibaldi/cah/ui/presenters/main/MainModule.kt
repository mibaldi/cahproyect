package com.mibaldi.cah.ui.presenters.main

import com.mibaldi.cah.data.repositories.Repository
import com.mibaldi.cah.domain.interactors.MainInteractor
import com.mibaldi.cah.domain.interactors.MainInteractorImpl
import com.mibaldi.cah.ui.activities.MainActivity
import dagger.Module
import dagger.Provides

@Module
class MainModule(val activity: MainActivity) {

    @Provides
    fun provideRepository(repository: Repository): MainInteractor {
        val interactor = MainInteractorImpl(repository)
        return interactor
    }
}