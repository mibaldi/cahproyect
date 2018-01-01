package com.mibaldi.cah.ui.presenters.configuration

import android.arch.lifecycle.ViewModel
import com.mibaldi.cah.router.Router
import javax.inject.Inject
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.domain.interactors.configuration.ConfigurationInteractor
import com.mibaldi.cah.ui.activities.ConfigurationActivity
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.ConfigurationContract
import java.lang.ref.WeakReference


class ConfigurationPresenter @Inject constructor(val router: Router, val configurationInteractor:ConfigurationInteractor): BasePresenter<ConfigurationContract.View>(), ConfigurationContract.Presenter {


    lateinit var model:MainViewModel
    override fun initializer(viewModel: ViewModel) {
        model = viewModel as MainViewModel
    }
    override fun changeUsername(username: String) {
        mView?.showProgress()
        configurationInteractor.changeUsername(username,{result ->
            mView?.hideProgress()
            if (result.second != null){
                mView?.showError(result.second?.message)
            }else {
                model.setCurrentUser(result.first!!)
                val weakReference = WeakReference(mView as ConfigurationActivity)
                router.closeActivity(weakReference)
            }
        })
    }
    override fun setModel(viewModel: ViewModel) {
        model = viewModel as MainViewModel
    }

}