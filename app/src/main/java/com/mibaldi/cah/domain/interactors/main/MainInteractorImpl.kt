package com.mibaldi.cah.domain.interactors.main

import android.util.Log
import com.mibaldi.cah.data.models.Game
import com.mibaldi.cah.data.models.firebase.GameFirebase
import com.mibaldi.cah.data.repositories.GameRepository
import com.mibaldi.cah.data.repositories.UserRepository
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


class MainInteractorImpl(val userRepository: UserRepository,val gameRepository: GameRepository) : MainInteractor {

    override fun getAllPlays(subscriber: Observer<List<Game>>) {
        val gameList = mutableListOf<Game>()
        val observer  = object : Observer<GameFirebase> {
            override fun onNext(gameFirebase: GameFirebase) {
               gameList.add(gameFirebase.toGame())
            }

            override fun onSubscribe(d: Disposable) {
                Log.d("Subscriber","New Subscriber")
            }

            override fun onError(e: Throwable) {
                Log.d("Subscriber",e.message)
            }

            override fun onComplete() {
                subscriber.onNext(gameList)
                subscriber.onComplete()
            }
        }
        gameRepository.getAllGames(observer)
    }

    override fun getCurrentPlayer(param: (Pair<String?,Error?>) -> Unit) {
        userRepository.changeUsername("Olga", param)
    }


}