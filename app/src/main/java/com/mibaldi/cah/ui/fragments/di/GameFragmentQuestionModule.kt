package com.mibaldi.cah.ui.fragments.di

import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.managers.TurnFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.fragments.GameFragmentQuestion
import com.mibaldi.cah.ui.presenters.game.fragments.GameFragmentQuestionPresenter
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
        fun provideGamePresenter(router: Router, turnManager : TurnFirebaseManager): GameFragmentQuestionPresenter {
            return GameFragmentQuestionPresenter(router, turnManager)
        }
    }

}