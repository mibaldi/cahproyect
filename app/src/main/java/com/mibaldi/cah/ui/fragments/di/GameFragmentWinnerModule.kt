package com.mibaldi.cah.ui.fragments.di

import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.fragments.GameFragmentResponses
import com.mibaldi.cah.ui.fragments.GameFragmentWinner
import com.mibaldi.cah.ui.presenters.game.fragments.GameFragmentResponsesPresenter
import com.mibaldi.cah.ui.presenters.game.fragments.GameFragmentWinnerPresenter
import dagger.Module
import dagger.Provides


@Module
abstract class GameFragmentWinnerModule {

    @Module
    companion object {

        @Provides
        internal fun provideGameFragmentWinnerView(gameFragmentWinner: GameFragmentWinner): GameFragmentWinner {
            return gameFragmentWinner
        }

        @JvmStatic
        @Provides
        fun provideGameFragmentWinnerPresenter(router: Router, gameManager : GameFirebaseManager): GameFragmentWinnerPresenter {
            return GameFragmentWinnerPresenter(router, gameManager)
        }
    }

}