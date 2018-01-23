package com.mibaldi.cah.ui.fragments.di

import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.fragments.GameFragmentResult
import com.mibaldi.cah.ui.presenters.game.fragments.GameFragmentResultPresenter
import dagger.Module
import dagger.Provides


@Module
abstract class GameFragmentResultModule {

    @Module
    companion object {

        @Provides
        internal fun provideGameFragmentResultView(gameFragmentResult: GameFragmentResult): GameFragmentResult {
            return gameFragmentResult
        }

        @JvmStatic
        @Provides
        fun provideGameFragmentResultPresenter(router: Router, gameManager : GameFirebaseManager): GameFragmentResultPresenter {
            return GameFragmentResultPresenter(router, gameManager)
        }
    }

}