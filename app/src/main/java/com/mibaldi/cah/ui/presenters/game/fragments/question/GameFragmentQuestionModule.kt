package com.mibaldi.cah.ui.presenters.game.fragments.question

import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.fragments.GameFragmentQuestion
import dagger.Module
import dagger.Provides


@Module
abstract class GameFragmentQuestionModule {

    @Module
    companion object {

        @Provides
        internal fun provideGameFragmentQuestionView(gameFragmentQuestion: GameFragmentQuestion): GameFragmentQuestion {
            return gameFragmentQuestion
        }

        @JvmStatic
        @Provides
        fun provideGamePresenter(router: Router, gameManager : GameFirebaseManager): GameFragmentQuestionPresenter {
            return GameFragmentQuestionPresenter(router, gameManager)
        }
    }

}