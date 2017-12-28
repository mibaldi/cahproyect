package com.mibaldi.cah.ui.presenters

import android.arch.lifecycle.Observer
import com.mibaldi.cah.domain.interactors.MainInteractor
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.activities.MainActivity
import com.mibaldi.cah.ui.views.MainContract
import com.mibaldi.cah.utils.app
import javax.inject.Inject
import android.arch.lifecycle.ViewModelProviders
import com.mibaldi.cah.base.presenters.actitivities.BasePresenter
import com.mibaldi.cah.ui.viewModels.MainViewModel


class MainPresenter(val context: MainActivity): BasePresenter<MainContract.View>(), MainContract.Presenter {


    @Inject
    lateinit var interactor: MainInteractor


    @Inject
    lateinit var router: Router

    val component by lazy { context.app.component.plus(MainModule(context)) }
    lateinit var model:MainViewModel


    private fun observeUser() {
        model.currentUser.observe(context, Observer { user ->
            user?.let {
                mView?.showCurrentUser(it)
            }
        })
    }
    override fun initializer() {
        component.inject(this)
        model = ViewModelProviders.of(context).get(MainViewModel::class.java)
        model.setCurrentUser("Mikel")
        observeUser()
    }

}