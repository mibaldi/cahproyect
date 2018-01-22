package com.mibaldi.cah.ui.fragments.di

import com.mibaldi.cah.ui.fragments.GameFragmentQuestion
import com.mibaldi.cah.ui.presenters.game.fragments.question.GameFragmentQuestionModule
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class GameFragmentQuestionProvider {

    @ContributesAndroidInjector(modules = arrayOf(GameFragmentQuestionModule::class))
    internal abstract fun provideGameFragmentQuestionFactory(): GameFragmentQuestion

}
