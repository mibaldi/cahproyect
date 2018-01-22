package com.mibaldi.cah.ui.fragments.di

import com.mibaldi.cah.ui.fragments.GameFragmentQuestion
import com.mibaldi.cah.ui.fragments.GameFragmentResponses
import com.mibaldi.cah.ui.presenters.game.fragments.question.GameFragmentQuestionModule
import com.mibaldi.cah.ui.presenters.game.fragments.responses.GameFragmentResponsesModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class GameFragmentResponsesProvider {

    @ContributesAndroidInjector(modules = arrayOf(GameFragmentResponsesModule::class))
    internal abstract fun provideGameFragmentResponsesFactory(): GameFragmentResponses

}
