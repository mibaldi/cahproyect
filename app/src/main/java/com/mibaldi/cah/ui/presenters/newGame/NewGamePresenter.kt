package com.mibaldi.cah.ui.presenters.newGame

import android.util.Log
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.data.models.Game
import com.mibaldi.cah.data.models.Player
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.views.NewGameContract
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class NewGamePresenter @Inject constructor(val router: Router, val gameManager: GameFirebaseManager): BasePresenter<NewGameContract.View>(), NewGameContract.Presenter {

    var mKey :String = ""
    override fun createGame() {
        val observer : Observer<String> = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                Log.d("Subscriber","New Subscriber")
            }

            override fun onNext(it: String) {
                mKey = it
            }

            override fun onError(e: Throwable) {
                Log.d("Subscriber",e.message)
            }

            override fun onComplete() {
                addPlayer(Player("Mikel"))
                addPlayer(Player("Pabjimcas"))
            }

        }
        gameManager.newGame(Game("miJuego"),observer)
    }


    fun addPlayer(player: Player){
        val observer  = object : Observer<Boolean>{
            override fun onNext(t: Boolean) {
                Log.d("AddPlayer","Juego creado $t")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d("Subscriber","New Subscriber")
            }

            override fun onError(e: Throwable) {
                Log.d("Subscriber",e.message)
            }

            override fun onComplete() {

            }
        }
        gameManager.addPlayer(mKey, player,observer)
    }

    fun initGame(){
        gameManager.isGamePrepared(mKey,object : Observer<String>{
            override fun onComplete() {
                if (mKey.isNotEmpty()){
                    gameManager.startRound(mKey)
                }
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