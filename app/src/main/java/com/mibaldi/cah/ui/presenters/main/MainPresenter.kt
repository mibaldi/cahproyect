package com.mibaldi.cah.ui.presenters.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.data.models.Game
import com.mibaldi.cah.data.models.Player
import com.mibaldi.cah.domain.interactors.main.MainInteractor
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.activities.MainActivity
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.MainContract
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class MainPresenter @Inject constructor(val router: Router,val interactor: MainInteractor, val gameManager: GameFirebaseManager): BasePresenter<MainContract.View>(), MainContract.Presenter {

    lateinit var model:MainViewModel
    val observerUser= Observer<String> { user -> user?.let {
        mView?.showCurrentUser(it)
    }}
    val observerGameList= Observer<List<Game>> { list -> list?.let {
        mView?.showGameList(it)
    }}

    override fun initializer(viewModel: ViewModel) {
        model = viewModel as MainViewModel
        mView?.observeUser(observerUser)
        mView?.observeList(observerGameList)

    }

    fun goToConfiguration() {
        router.goToConfiguration()
    }

    fun goToNewGame() {
        router.goToNewGame()
    }
    override fun getGameList() {
        interactor.getAllPlays(object: io.reactivex.Observer<List<Game>>{
            override fun onNext(list: List<Game>) {
                model.setGameList(list)
            }

            override fun onComplete() {

            }

            override fun onError(e: Throwable) {

            }

            override fun onSubscribe(d: Disposable) {

            }
        })
    }

    override fun joinGame(mKey : String) {
        val observer  = object : io.reactivex.Observer<Boolean> {
            override fun onNext(t: Boolean) {
                Log.d("AddPlayer","Juego creado $t")
                router.gotToGame(mKey)
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
        gameManager.addPlayer(mKey, Player(model.currentUser.value ?: "Pablo"),observer)
    }

    override fun showJoinGameAlert() {
        mView?.alertJoinGame()
    }

}