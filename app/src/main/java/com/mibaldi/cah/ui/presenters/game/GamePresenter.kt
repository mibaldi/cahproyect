package com.mibaldi.cah.ui.presenters.game

import android.util.Log
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.data.models.Game
import com.mibaldi.cah.data.models.Player
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.views.GameContract
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class GamePresenter @Inject constructor(val router: Router, val gameManager: GameFirebaseManager): BasePresenter<GameContract.View>(), GameContract.Presenter {


    lateinit var mIdGame : String

    override fun initialize(idGame: String) {
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
    }

    fun sharedWhatsapp(){
        router.sharedWhatsapp(mIdGame)
    }

}
