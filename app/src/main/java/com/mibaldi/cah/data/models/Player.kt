package com.mibaldi.cah.data.models

import com.mibaldi.cah.data.models.firebase.PlayerFirebase
import com.mibaldi.cah.data.parsers.toCardFirebaseRefList

data class Player(var username:String, var cards: List<Card> = mutableListOf() ){
    fun toPlayerFirebase() = PlayerFirebase(username,cards.toCardFirebaseRefList())
    enum class Type(name:String){
        Narrator("Narrator"),
        Player("Player")
    }
}