package com.mibaldi.cah.data.models.firebase

import com.google.firebase.database.Exclude
import com.mibaldi.cah.data.models.Card
import com.mibaldi.cah.data.models.Player

data class PlayerFirebase(var id: String, var cartas: List<Int> = mutableListOf()){
    fun toPlayer(cards:List<Card>) = Player(id,cards)
}