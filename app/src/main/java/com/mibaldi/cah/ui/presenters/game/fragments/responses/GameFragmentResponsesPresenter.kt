package com.mibaldi.cah.ui.presenters.game.fragments.responses

import com.mibaldi.cah.base.presenters.fragments.BaseFragmentPresenter
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.GameFragmentContract
import javax.inject.Inject


class GameFragmentResponsesPresenter @Inject constructor(val router: Router, val gameManager: GameFirebaseManager): BaseFragmentPresenter<GameFragmentContract.FragmentResponsesView>(), GameFragmentContract.FragmentResponsesPresenter {


    lateinit var mIdGame : String
    lateinit var mModel: MainViewModel
    override fun initialize(idGame: String, type: String) {
        initGame()
        mIdGame = idGame
        mView?.setType(type)
    }

    fun initGame(){
    }
    fun changeStateRound(){

        if (mIdGame.isNotEmpty()){
            gameManager.startRound(mIdGame)
        }
    }
}
