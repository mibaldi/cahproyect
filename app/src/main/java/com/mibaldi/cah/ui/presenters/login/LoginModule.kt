package com.mibaldi.cah.ui.presenters.login

import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.managers.SharedPreferencesManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.activities.LoginActivity
import com.mibaldi.cah.ui.views.LoginContract
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class LoginModule {

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideLoginPresenter(router: Router, prefs: SharedPreferencesManager): LoginPresenter {
            return LoginPresenter(router,prefs)
        }
    }

    @Binds
    internal abstract fun provideLoginActivity(loginActivity: LoginActivity): BaseMvpActivity<LoginContract.View, LoginPresenter>

}