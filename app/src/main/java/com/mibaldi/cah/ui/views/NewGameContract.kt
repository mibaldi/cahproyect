package com.mibaldi.cah.ui.views

import com.mibaldi.cah.base.presenters.activities.BaseMvpPresenter
import com.mibaldi.cah.base.views.BaseMvpView

object NewGameContract {
    interface View : BaseMvpView {
        fun showProgress()
        fun hideProgress()
        fun showError(message: String?)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun createGame()
    }
}