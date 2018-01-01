package com.mibaldi.cah.ui.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel



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

}