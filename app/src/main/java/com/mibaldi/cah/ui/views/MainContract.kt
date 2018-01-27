package com.mibaldi.cah.ui.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import com.mibaldi.cah.base.presenters.activities.BaseMvpPresenter
import com.mibaldi.cah.base.views.BaseMvpView
import com.mibaldi.cah.data.models.uimodels.Game


object MainContract {
    interface View : BaseMvpView {
        fun showCurrentUser(user:String)
        fun showError(message: String?)
        fun observeUser(observer:Observer<String>)
        fun alertJoinGame()
        fun observeList(observerGameList: Observer<List<Game>>)
        fun showGameList(listGame: List<Game>)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun  initializer(viewModel: ViewModel)
        fun joinGame(mKey: String)
        fun showJoinGameAlert()
        fun getGameList()

    }
}