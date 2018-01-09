package com.mibaldi.cah.ui.presenters.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.domain.interactors.main.MainInteractor
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.activities.MainActivity
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.MainContract
import com.mibaldi.cah.utils.ViewModelFactory
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject


class MainPresenter @Inject constructor(val router: Router,val interactor: MainInteractor): BasePresenter<MainContract.View>(), MainContract.Presenter {


    lateinit var model:MainViewModel
    val observer= Observer<String> { user -> user?.let {
        mView?.showCurrentUser(it)
    }}
    override fun initializer(viewModel: ViewModel) {
        model = viewModel as MainViewModel
        mView?.observeUser(observer)

    }

    fun goToConfiguration() {
        router.goToConfiguration()
    }

    fun goToNewGame() {
        router.goToNewGame()
    }
    override fun getCurrentUser() {
        model.setCurrentUser("Mikel2")
        interactor.getCurrentPlayer { result ->
            if (result.second != null){
                mView?.showError(result.second?.message)
            }else {
                result.first?.let {
                    model.setCurrentUser(it)
                }
            }
        }
        interactor.getAllPlays()
    }

}