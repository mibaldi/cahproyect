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
import com.mibaldi.cah.ui.views.GameContract
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class GamePresenter @Inject constructor(val router: Router, val gameManager: GameFirebaseManager): BasePresenter<GameContract.View>(), GameContract.Presenter {


    var mGame : Game? = null
    var mUser: String? = null
    lateinit var mModel: MainViewModel
    var currentState = -1L
    override fun initialize(model: MainViewModel) {
        mModel = model
        mGame = mModel.currentGame.value
        mUser = mModel.currentUser.value

        val observer : Observer<Long> = object : Observer<Long> {
            override fun onSubscribe(d: Disposable) {
                Log.d("Subscriber","New Subscriber")
            }

            override fun onNext(numPlayers: Long) {
                mView?.changeNumPlayers(numPlayers)
            }

            override fun onError(e: Throwable) {
                Log.d("Subscriber",e.message)
            }

            override fun onComplete() {
                mView?.showProgress()
            }

        }
        mGame?.let {
            gameManager.getNumPlayers(it,observer)
            initGame()
        }
    }

    fun initGame(){
        mGame?.let {
            val key = it.keyGame
            gameManager.stateOfTurn(it.keyGame, object : Observer<Turn> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(turn: Turn) {
                    with(turn){
                        status?.let {
                            mView?.hideProgress()
                            currentState = it
                            mView?.showTurn(it)
                            mView?.changeState(key,turn.turnNumber!!)
                        }
                        possibles?.let {
                            Observable.zip(possibles,{
                                Log.d("POSIBLES",it.toString())
                            }).subscribe(object : Observer<Int>{
                                override fun onComplete() {
                                }

                                override fun onSubscribe(d: Disposable) {
                                }

                                override fun onNext(t: Int) {
                                    Log.d("OnnextPosibles",t.toString())
                                }

                                override fun onError(e: Throwable) {
                                }

                            })
                        }
                        question?.subscribe(object : Observer<Question>{
                            override fun onComplete() {
                            }

                            override fun onSubscribe(d: Disposable) {
                            }

                            override fun onNext(t: Question) {
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
