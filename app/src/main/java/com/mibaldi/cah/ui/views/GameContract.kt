package com.mibaldi.cah.ui.views

import com.mibaldi.cah.base.presenters.activities.BaseMvpPresenter
import com.mibaldi.cah.base.views.BaseMvpView

object GameContract {
    interface View : BaseMvpView {
        fun showProgress()
        fun hideProgress()
        fun showError(message: String?)
        fun changeNumPlayers(it: Long)
        fun showButton()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun initialize(idGame: String)
        fun changeStateRound()
    }
}