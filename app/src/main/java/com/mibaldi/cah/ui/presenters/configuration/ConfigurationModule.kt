package com.mibaldi.cah.ui.presenters.configuration



import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.data.repositories.UserRepository
import com.mibaldi.cah.domain.interactors.configuration.ConfigurationInteractor
import com.mibaldi.cah.domain.interactors.configuration.ConfigurationInteractorImpl
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.activities.ConfigurationActivity
import com.mibaldi.cah.ui.views.ConfigurationContract
import com.mibaldi.cah.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ConfigurationModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideConfigurationPresenter(router: Router,interactor: ConfigurationInteractor): ConfigurationPresenter {
            return ConfigurationPresenter(router,interactor)
        }
        @JvmStatic
        @Provides
        fun provideMainInteractor(userRepository: UserRepository): ConfigurationInteractor {
            return ConfigurationInteractorImpl(userRepository)
        }
    }

    @Binds
    internal abstract fun provideConfigurationActivity(mainActivity: ConfigurationActivity): BaseMvpActivity<ConfigurationContract.View, ConfigurationPresenter>

}