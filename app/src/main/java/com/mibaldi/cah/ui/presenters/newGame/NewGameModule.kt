package com.mibaldi.cah.ui.presenters.newGame

import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.activities.NewGameActivity
import com.mibaldi.cah.ui.views.NewGameContract
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class NewGameModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideNewGamePresenter(router: Router, gameManager : GameFirebaseManager): NewGamePresenter {
            return NewGamePresenter(router,gameManager)
        }
    }

    @Binds
    internal abstract fun provideConfigurationActivity(activity: NewGameActivity): BaseMvpActivity<NewGameContract.View, NewGamePresenter>

}