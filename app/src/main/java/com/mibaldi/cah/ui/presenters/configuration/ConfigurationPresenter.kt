package com.mibaldi.cah.ui.presenters.configuration

import com.mibaldi.cah.domain.interactors.MainInteractor
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.utils.app
import javax.inject.Inject
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.ui.activities.ConfigurationActivity
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.ConfigurationContract


class ConfigurationPresenter: BasePresenter<ConfigurationContract.View>(), ConfigurationContract.Presenter {


    @Inject
    lateinit var interactor: MainInteractor


    @Inject
    lateinit var router: Router

    val component by lazy {
        val activity = mView?.getMyActivity() as ConfigurationActivity
        activity.let {
            it.app.component.plus(ConfigurationModule(it))
        }
    }
    lateinit var model:MainViewModel


    override fun initializer() {
        component.inject(this)

    }


}