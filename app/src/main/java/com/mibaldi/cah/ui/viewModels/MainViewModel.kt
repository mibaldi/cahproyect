package com.mibaldi.cah.ui.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.mibaldi.cah.data.models.uimodels.Game


class MainViewModel: ViewModel() {

    var currentUser = MutableLiveData<String>()
    fun getCurrentUser(): LiveData<String> {
        if (currentUser.value == null){
            currentUser.value = "Mikel"
        }
        return currentUser
    }

    fun setCurrentUser(user: String){
        currentUser.value = user
    }

    var currentGame = MutableLiveData<Game>()
    fun setCurrentGame(game : Game){
        currentGame.value = game;
    }

    fun getCurrentGame(): LiveData<Game>{
        if (currentGame.value == null){
            currentGame.value = Game("", "")
        }
        return currentGame
    }

    var gameList = MutableLiveData<List<Game>>()
    fun getGameList(): LiveData<List<Game>> {
        if (gameList.value == null){
            gameList.value = mutableListOf()
        }
        return gameList
    }

    fun setGameList(list: List<Game>){
        gameList.value = list
    }

}