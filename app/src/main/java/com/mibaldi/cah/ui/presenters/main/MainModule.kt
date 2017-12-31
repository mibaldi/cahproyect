package com.mibaldi.cah.ui.presenters.main

import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.data.repositories.GameRepository
import com.mibaldi.cah.data.repositories.UserRepository
import com.mibaldi.cah.domain.interactors.main.MainInteractor
import com.mibaldi.cah.domain.interactors.main.MainInteractorImpl
import com.mibaldi.cah.router.Router
import dagger.Binds
import dagger.Module
import com.mibaldi.cah.ui.activities.MainActivity
import com.mibaldi.cah.ui.views.MainContract
import com.mibaldi.cah.utils.ViewModelFactory
import dagger.Provides



@Module
abstract class MainModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideMainPresenter(router: Router, interactor: MainInteractor,viewModelFactory: ViewModelFactory): MainPresenter {
            return MainPresenter(router, interactor,viewModelFactory)
        }
        @JvmStatic
        @Provides
        fun provideMainInteractor(userRepository: UserRepository,gameRepository: GameRepository): MainInteractor {
            return MainInteractorImpl(userRepository,gameRepository)
        }
    }

    @Binds
    abstract fun provideMainActivity(mainActivity: MainActivity): BaseMvpActivity<MainContract.View,MainPresenter>

}