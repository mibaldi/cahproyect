package com.mibaldi.cah.ui.presenters.newGame

import android.util.Log
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.data.models.Game
import com.mibaldi.cah.data.models.Player
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.NewGameContract
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class NewGamePresenter @Inject constructor(val router: Router, val gameManager: GameFirebaseManager): BasePresenter<NewGameContract.View>(), NewGameContract.Presenter {

    var mGame: Game? = null

    lateinit var mModel: MainViewModel
    override fun createGame() {
        var userLogued = mModel.currentUser.value ?: "Mikel"
        mGame = Game(userLogued,"miJuego")

        val observer : Observer<String> = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {
                Log.d("Subscriber","New Subscriber")
            }

            override fun onNext(it: String) {
                mGame?.keyGame = it
            }

            override fun onError(e: Throwable) {
                Log.d("Subscriber",e.message)
            }

            override fun onComplete() {
                addPlayer(Player(userLogued))
                mView?.showSharedAlert()
            }

        }
        mGame?.let {
            gameManager.newGame(it,observer)
        }
    }

    override fun goToGameActivity() {
        mGame?.let {
            if(it.keyGame.isNotEmpty()) {
                mModel.setCurrentGame(it)
                router.gotToGame(it.keyGame)
            }
        }

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
        mGame?.let {
            gameManager.addPlayer(it.keyGame, player, observer)
        }
    }
    override fun initializer(viewModel: MainViewModel) {
        mModel = viewModel

    }


}