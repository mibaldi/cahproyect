package com.mibaldi.cah.ui.presenters.configuration

import android.arch.lifecycle.ViewModelProviders
import com.mibaldi.cah.router.Router
import javax.inject.Inject
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.domain.interactors.configuration.ConfigurationInteractor
import com.mibaldi.cah.ui.activities.ConfigurationActivity
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.ConfigurationContract
import com.mibaldi.cah.utils.ViewModelFactory


class ConfigurationPresenter @Inject constructor(val router: Router, val configurationInteractor:ConfigurationInteractor,val viewModelFactory: ViewModelFactory): BasePresenter<ConfigurationContract.View>(), ConfigurationContract.Presenter {

    override fun initializer() {

    }
    override fun changeUsername(username: String) {
        mView?.showProgress()
        configurationInteractor.changeUsername(username,{result ->
            if (result.second != null){
                mView?.showError(result.second?.message)
            }else {
                val activity = mView?.getMyActivity() as ConfigurationActivity
                val model = ViewModelProviders.of(activity,viewModelFactory).get(MainViewModel::class.java)
                model.setCurrentUser(result.first!!)
                router.closeActivity(mView?.getMyActivity()!!)
            }
        })
    }

}