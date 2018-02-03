package com.mibaldi.cah.data.models.uimodels

import com.mibaldi.cah.data.models.firebase.TurnFirebase
import io.reactivex.Observable


data class Turn(var turnNumber: String? ,var narrator: String, var status: Long?, var question: Observable<Question>?, var possibles: List<Observable<Answer>>?, var winner: String?){
    /*fun toTurnFirebase() = {
        TurnFirebase(narrator,status,question?.idQuestion,winner,possibles?.map { Pair(it.userId,it.idAnswer) })
    }*/
}
