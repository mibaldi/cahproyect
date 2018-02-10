package com.mibaldi.cah.ui.presenters.game

import android.util.Log
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.data.models.uimodels.Answer
import com.mibaldi.cah.data.models.uimodels.Game
import com.mibaldi.cah.data.models.uimodels.Question
import com.mibaldi.cah.data.models.uimodels.Turn
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.viewModels.TurnViewModel
import com.mibaldi.cah.ui.views.GameContract
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class GamePresenter @Inject constructor(val router: Router, val gameManager: GameFirebaseManager): BasePresenter<GameContract.View>(), GameContract.Presenter {


    var mGame : Game? = null
    var mUser: String? = null
    lateinit var mModel: MainViewModel
    lateinit var mTurnModel: TurnViewModel
    var currentState = -1L
    val observerTurnNumber = android.arch.lifecycle.Observer<String> { turnNumber ->
        turnNumber?.let {
            mView?.showTurn(it)
        }
    }
    val observerNarrator = android.arch.lifecycle.Observer<String> { narrator ->
        narrator?.let {
            mView?.showNarrator(it)
        }
    }
    val observerStatus = android.arch.lifecycle.Observer<Long> { status ->
        status?.let {
            if (idGame.isNotEmpty()){
                mView?.showStatus(idGame,it)
            }

        }
    }

    override fun initialize(model: MainViewModel,turn: TurnViewModel) {
        initGameModel(model,turn)
    }

    private fun initGameModel(model: MainViewModel,turn:TurnViewModel) {
        mModel = model
        mGame = mModel.currentGame.value
        mUser = mModel.currentUser.value
        mTurnModel = turn
        mView?.let {
            with(it){
                observeNarrator(observerNarrator)
                observeStatus(observerStatus)
                observeTurnNumber(observerTurnNumber)
            }
        }

        mGame?.let {
            gameManager.getNumPlayers(it, object : io.reactivex.Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    Log.d("Subscriber", "New Subscriber")
                }

                override fun onNext(numPlayers: Long) {
                    mView?.changeNumPlayers(numPlayers)
                }

                override fun onError(e: Throwable) {
                    Log.d("Subscriber", e.message)
                }

                override fun onComplete() {
                    mView?.showProgress()
                }

            })
            initGame()
        }
    }

    private lateinit var idGame: String

    fun initGame(){
        mGame?.let {
            idGame = it.keyGame
            gameManager.stateOfTurn(it.keyGame, object : io.reactivex.Observer<Turn> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(turn: Turn) {
                    with(turn){
                        status?.let {
                            mView?.hideProgress()
                            currentState = it
                            mTurnModel.status.value = it
                            mTurnModel.turnNumber.value = turnNumber
                        }
                        narrator.let{
                            mTurnModel.narrator.value = it
                        }
                        winner?.let {
                            mTurnModel.winner.value = it
                        }
                        possibles?.let {
                            Observable.zip(possibles,{
                                 it.map { it as Answer }
                            }).subscribe(object : io.reactivex.Observer<List<Answer>> {
                                override fun onComplete() {
                                }

                                override fun onSubscribe(d: Disposable) {
                                }
                                override fun onNext(posiblesList: List<Answer>) {
                                    mTurnModel.possibles.value = posiblesList
                                    Log.d("OnnextPosibles",posiblesList.toString())
                                }

                                override fun onError(e: Throwable) {
                                }

                            })
                        }
                        question?.subscribe(object : io.reactivex.Observer<Question> {
                            override fun onComplete() {
                            }

                            override fun onSubscribe(d: Disposable) {
                            }

                            override fun onNext(question: Question) {
                                mTurnModel.question.value = question
                                Log.d("OnnextQuestion",question.toString())
                            }

                            override fun onError(e: Throwable) {
                            }

                        })
                    }

                }

                override fun onError(e: Throwable) {
                }

            })
        }
    }

    override fun startRound() {
        mGame?.let {
            gameManager.startRound(it.keyGame)
        }
    }
    override fun changeStateRound(){

        mGame?.let {
            gameManager.startRound(it.keyGame)
        }
    }
}
