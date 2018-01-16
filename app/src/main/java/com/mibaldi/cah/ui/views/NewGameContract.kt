package com.mibaldi.cah.ui.views

import com.mibaldi.cah.base.presenters.activities.BaseMvpPresenter
import com.mibaldi.cah.base.views.BaseMvpView
import com.mibaldi.cah.ui.viewModels.MainViewModel

object NewGameContract {
    interface View : BaseMvpView {
        fun showProgress()
        fun hideProgress()
        fun showError(message: String?)
        fun showSharedAlert()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun createGame()
        fun goToGameActivity()
        fun initializer(viewModel: MainViewModel)
    }
}