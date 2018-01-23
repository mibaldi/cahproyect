package com.mibaldi.cah.ui.fragments.di

import com.mibaldi.cah.ui.fragments.GameFragmentQuestion
import com.mibaldi.cah.ui.fragments.GameFragmentResponses
import com.mibaldi.cah.ui.fragments.GameFragmentResult
import com.mibaldi.cah.ui.fragments.GameFragmentWinner
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class GameFragmentsProvider {

    @ContributesAndroidInjector(modules = arrayOf(GameFragmentQuestionModule::class))
    internal abstract fun provideGameFragmentQuestionFactory(): GameFragmentQuestion

    @ContributesAndroidInjector(modules = arrayOf(GameFragmentResponsesModule::class))
    internal abstract fun provideGameFragmentResponsesFactory(): GameFragmentResponses

    @ContributesAndroidInjector(modules = arrayOf(GameFragmentWinnerModule::class))
    internal abstract fun provideGameFragmentWinnerFactory(): GameFragmentWinner

    @ContributesAndroidInjector(modules = arrayOf(GameFragmentResultModule::class))
    internal abstract fun provideGameFragmentResultFactory(): GameFragmentResult

}
