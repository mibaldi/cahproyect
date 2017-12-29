package com.mibaldi.cah.ui.presenters.main

import android.arch.lifecycle.Observer
import com.mibaldi.cah.domain.interactors.MainInteractor
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.activities.MainActivity
import com.mibaldi.cah.ui.views.MainContract
import com.mibaldi.cah.utils.app
import javax.inject.Inject
import android.arch.lifecycle.ViewModelProviders
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.ui.viewModels.MainViewModel


class MainPresenter: BasePresenter<MainContract.View>(), MainContract.Presenter {


    @Inject
    lateinit var interactor: MainInteractor


    @Inject
    lateinit var router: Router

    val component by lazy {
        val activity = mView?.getMyActivity() as MainActivity
        activity.let {
            it.app.component.plus(MainModule(it))
        }
    }
    lateinit var model:MainViewModel


    private fun observeUser() {
        model.currentUser.observe(mView?.getMyActivity() as MainActivity, Observer { user ->
            user?.let {
                mView?.showCurrentUser(it)
            }
        })
    }
    override fun initializer() {
        component.inject(this)
        val activity = mView?.getMyActivity() as MainActivity
        model = ViewModelProviders.of(activity).get(MainViewModel::class.java)
        model.setCurrentUser("Mikel")
        observeUser()
    }

    fun goToConfiguration() {
        router.goToConfiguration()
    }

}