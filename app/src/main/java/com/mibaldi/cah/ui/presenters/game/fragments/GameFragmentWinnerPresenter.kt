package com.mibaldi.cah.ui.presenters.game.fragments

import com.mibaldi.cah.base.presenters.fragments.BaseFragmentPresenter
import com.mibaldi.cah.data.models.uimodels.Answer
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.GameFragmentContract
import javax.inject.Inject


class GameFragmentWinnerPresenter @Inject constructor(val router: Router, val gameManager: GameFirebaseManager): BaseFragmentPresenter<GameFragmentContract.FragmentWinnerView>(), GameFragmentContract.FragmentWinnerPresenter {


    lateinit var mIdGame : String
    val observerWinner = android.arch.lifecycle.Observer<String> { winner ->
        winner?.let {
            mView?.showWinner(it)
        }
    }
    override fun initialize(idGame: String, type: String) {
        mIdGame = idGame
        mView?.observeWinner(observerWinner)
    }


}
