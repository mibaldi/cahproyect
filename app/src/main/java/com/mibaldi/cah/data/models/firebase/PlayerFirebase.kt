package com.mibaldi.cah.data.models.firebase

import com.mibaldi.cah.data.models.uimodels.Card
import com.mibaldi.cah.data.models.uimodels.Player

data class PlayerFirebase(var id: String = "", var cartas: List<Int> = mutableListOf()){
    fun toPlayer(cards:List<Card>) = Player(id, cards)
}