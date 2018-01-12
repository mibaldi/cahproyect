package com.mibaldi.cah.data.models

import com.mibaldi.cah.data.models.firebase.GameConfigFirebase
import com.mibaldi.cah.data.models.firebase.GameFirebase

data class Game(var name: String, var numCardsByPlayer: Int = 3, var numPlayers: Int = 2, var rounds: Int = 2){
    fun toGameFirebase() = GameFirebase(name, GameConfigFirebase(numCardsByPlayer, numPlayers, rounds))
}
