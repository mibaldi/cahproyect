package com.mibaldi.cah.data.models

import com.mibaldi.cah.data.models.firebase.TurnFirebase


data class Turn(var turnNumber: String? ,var narrator: String, var status: Long?, var question: String?, var possibles: List<Pair<String,String>>?, var winner: String?){
    fun toTurnFirebase() = TurnFirebase(narrator,status,question,winner,possibles)
}
