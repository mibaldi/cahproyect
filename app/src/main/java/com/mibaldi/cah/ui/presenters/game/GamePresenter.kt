package com.mibaldi.cah.ui.presenters.game

import android.util.Log
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.data.models.Game
import com.mibaldi.cah.data.models.Player
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.activities.GameActivity
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.GameContract
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class GamePresenter @Inject constructor(val router: Router, val gameManager: GameFirebaseManager): BasePresenter<GameContract.View>(), GameContract.Presenter {


    lateinit var mIdGame : String
    lateinit var mModel: MainViewModel
    override fun initialize(idGame: String,model: MainViewModel) {
        mModel = model
        mIdGame = idGame

        val observer : Observer<Long> = object : Observer<Long> {
            override fun onSubscribe(d: Disposable) {
                Log.d("Subscriber","New Subscriber")
            }

            override fun onNext(it: Long) {
                mView?.changeNumPlayers(it)
            }

            override fun onError(e: Throwable) {
                Log.d("Subscriber",e.message)
            }

            override fun onComplete() {
            }

        }
        gameManager.getNumPlayers(mIdGame,observer)
        initGame()
    }

    fun sharedWhatsapp(){
        router.sharedWhatsapp(mView?.getMyActivity() as GameActivity,mIdGame)
    }
    fun initGame(){
        gameManager.whoIsRoundPlayer(mIdGame,object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                Log.d("Subscriber","New Subscriber")
            }

            override fun onComplete() {
            }

            override fun onError(e: Throwable) {
            }

            override fun onNext(username: String) {
                if(username == mModel.currentUser.value){
                    gameManager.isGamePrepared(mIdGame,object : Observer<String>{
                        override fun onComplete() {
                            mView?.showButton()
                        }
                        override fun onError(e: Throwable) {
                            Log.d("isGamePrepared","Error ${e.message}")
                        }
                        override fun onSubscribe(d: Disposable) {
                            Log.d("isGamePrepared","Subscripcion Juego preparado ${d.isDisposed}")
                        }

                        override fun onNext(t: String) {
                            Log.d("isGamePrepared","Juego preparado $t")
                        }
                    })
                }
            }

        })

    }
    override fun changeStateRound(){

        if (mIdGame.isNotEmpty()){
            gameManager.startRound(mIdGame)
        }
    }
}
