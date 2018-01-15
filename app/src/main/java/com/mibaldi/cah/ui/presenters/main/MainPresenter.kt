package com.mibaldi.cah.ui.presenters.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.data.models.Player
import com.mibaldi.cah.domain.interactors.main.MainInteractor
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.MainContract
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class MainPresenter @Inject constructor(val router: Router,val interactor: MainInteractor, val gameManager: GameFirebaseManager): BasePresenter<MainContract.View>(), MainContract.Presenter {

    lateinit var model:MainViewModel
    val observer= Observer<String> { user -> user?.let {
        mView?.showCurrentUser(it)
    }}
    override fun initializer(viewModel: ViewModel) {
        model = viewModel as MainViewModel
        mView?.observeUser(observer)

    }

    fun goToConfiguration() {
        router.goToConfiguration()
    }

    fun goToNewGame() {
        router.goToNewGame()
    }
    override fun getCurrentUser() {
        model.setCurrentUser("Mikel2")
        interactor.getCurrentPlayer { result ->
            if (result.second != null){
                mView?.showError(result.second?.message)
            }else {
                result.first?.let {
                    model.setCurrentUser(it)
                }
            }
        }
        interactor.getAllPlays()
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
        gameManager.addPlayer(mKey, Player("Pablo"),observer)
    }

    override fun showJoinGameAlert() {
        mView?.alertJoinGame()
    }

}