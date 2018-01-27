package com.mibaldi.cah.data.models.uimodels

import com.mibaldi.cah.data.models.firebase.TurnFirebase


data class Turn(var turnNumber: String? ,var narrator: String, var status: Long?, var question: Question?, var possibles: List<Answer>?, var winner: String?){
    fun toTurnFirebase() = {
        TurnFirebase(narrator,status,question?.idQuestion,winner,possibles?.map { Pair(it.userId,it.idAnswer) })
    }
}
