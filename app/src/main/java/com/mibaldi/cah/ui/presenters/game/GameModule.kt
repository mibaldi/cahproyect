package com.mibaldi.cah.ui.presenters.game

import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.activities.NewGameActivity
import com.mibaldi.cah.ui.views.NewGameContract
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class GameModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideGamePresenter(router: Router, gameManager : GameFirebaseManager): GamePresenter {
            return GamePresenter(router,gameManager)
        }
    }

}