package com.mibaldi.cah.ui.views

import com.mibaldi.cah.base.presenters.activities.BaseMvpPresenter
import com.mibaldi.cah.base.views.BaseMvpView
import com.mibaldi.cah.ui.viewModels.MainViewModel

object GameContract {
    interface View : BaseMvpView {
        fun showProgress()
        fun hideProgress()
        fun showError(message: String?)
        fun changeNumPlayers(it: Long)
        fun showButton()
        fun changeState(state: String)
        fun showTurn(turn: Long)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun initialize(model: MainViewModel)
        fun changeStateRound()
        fun startRound()
    }
}