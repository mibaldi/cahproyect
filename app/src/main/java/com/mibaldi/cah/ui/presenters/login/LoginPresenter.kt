package com.mibaldi.cah.ui.presenters.login

import android.util.Log
import com.mibaldi.cah.router.Router
import javax.inject.Inject
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.managers.SharedPreferencesManager
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.LoginContract


class LoginPresenter @Inject constructor(val router: Router, val prefs: SharedPreferencesManager): BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    lateinit var model:MainViewModel

    override fun initialize(viewModel: MainViewModel) {
        model = viewModel
        prefs.username?.let {
            router.goToMain()
        }
    }

    override fun signIn(username: String) {

        Log.d("USUSU",username)
        prefs.username = username
        model.setCurrentUser(username)
        router.goToMain()
    }
}