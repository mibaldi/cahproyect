package com.mibaldi.cah.ui.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.mibaldi.cah.data.models.Game


class MainViewModel: ViewModel() {

    var currentUser = MutableLiveData<String>()
    fun getCurrentUser(): LiveData<String> {
        if (currentUser.value == null){
            currentUser.value = ""
        }
        return currentUser
    }

    fun setCurrentUser(user: String){
        currentUser.value = user
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