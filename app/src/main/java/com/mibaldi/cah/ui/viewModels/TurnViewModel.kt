package com.mibaldi.cah.ui.viewModels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mibaldi.cah.data.models.uimodels.Answer
import com.mibaldi.cah.data.models.uimodels.Question

class TurnViewModel: ViewModel(){

    var turnNumber =  MutableLiveData<String>()
    var narrator = MutableLiveData<String>()
    var status = MutableLiveData<Long>()
    var question = MutableLiveData<Question>()
    var possibles =  MutableLiveData<List<Answer>>()
    fun getPossibles():LiveData<List<Answer>>{
        if (possibles.value == null){
            possibles.value = mutableListOf()
        }
        return possibles
    }
    var winner =  MutableLiveData<String>()

    fun clear(){
        turnNumber.value = null
        narrator.value = null
        status.value = null
        question.value = null
        possibles.value = null
        winner.value = null
    }
}