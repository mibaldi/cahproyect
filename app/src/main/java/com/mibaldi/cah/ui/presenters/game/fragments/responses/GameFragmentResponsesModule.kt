package com.mibaldi.cah.ui.presenters.game.fragments.responses

import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.fragments.GameFragmentQuestion
import com.mibaldi.cah.ui.fragments.GameFragmentResponses
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
        fun provideGameFragmentResponsesPresenter(router: Router, gameManager : GameFirebaseManager): GameFragmentResponsesPresenter {
            return GameFragmentResponsesPresenter(router, gameManager)
        }
    }

}