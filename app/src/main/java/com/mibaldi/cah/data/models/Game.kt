package com.mibaldi.cah.data.models

data class Game(var name: String, var numCardsByPlayer: Int = 3, var numPlayers: Int = 2, var rounds: Int = 2)
