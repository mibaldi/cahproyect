package com.mibaldi.cah.ui.fragments.di

import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.managers.TurnFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.fragments.GameFragmentResponses
import com.mibaldi.cah.ui.presenters.game.fragments.GameFragmentResponsesPresenter
import dagger.Module
import dagger.Provides


@Module
abstract class GameFragmentResponsesModule {

    @Module
    companion object {

        @Provides
        internal fun provideGameFragmentResponsesView(gameFragmentResponses: GameFragmentResponses): GameFragmentResponses {
            return gameFragmentResponses
        }

        @JvmStatic
        @Provides
        fun provideGameFragmentResponsesPresenter(router: Router, turnManager : TurnFirebaseManager): GameFragmentResponsesPresenter {
            return GameFragmentResponsesPresenter(router, turnManager)
        }
    }

}