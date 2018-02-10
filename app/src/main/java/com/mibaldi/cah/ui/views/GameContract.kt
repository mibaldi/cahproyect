package com.mibaldi.cah.ui.views

import android.arch.lifecycle.Observer
import com.mibaldi.cah.base.presenters.activities.BaseMvpPresenter
import com.mibaldi.cah.base.views.BaseMvpView
import com.mibaldi.cah.data.models.uimodels.Answer
import com.mibaldi.cah.data.models.uimodels.Question
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.viewModels.TurnViewModel

object GameContract {
    interface View : BaseMvpView {
        fun showProgress()
        fun hideProgress()
        fun showError(message: String?)
        fun changeNumPlayers(it: Long)
        fun showButton()

        fun observeTurnNumber(observerTurnNumber : Observer<String>)
        fun observeNarrator(observerNarrator : Observer<String>)
        fun observeStatus(observerStatus : Observer<Long>)

        fun showTurn(turn: String)
        fun showNarrator(narrator: String)
        fun showStatus(idGame: String,status: Long)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun initialize(model: MainViewModel,turn: TurnViewModel)
        fun changeStateRound()
        fun startRound()
    }
}