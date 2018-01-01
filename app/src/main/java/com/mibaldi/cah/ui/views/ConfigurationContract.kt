package com.mibaldi.cah.ui.views

import android.arch.lifecycle.ViewModel
import com.mibaldi.cah.base.presenters.activities.BaseMvpPresenter
import com.mibaldi.cah.base.views.BaseMvpView


object ConfigurationContract {
    interface View : BaseMvpView {
        fun showProgress()
        fun hideProgress()
        fun showError(message: String?)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun initializer(viewModel: ViewModel)
        fun changeUsername(username: String)
        fun setModel(viewModel: ViewModel)
    }
}