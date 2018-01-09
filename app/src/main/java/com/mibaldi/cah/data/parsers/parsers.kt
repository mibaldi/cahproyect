package com.mibaldi.cah.data.parsers

import com.mibaldi.cah.data.models.firebase.GameConfigFirebase
import com.mibaldi.cah.data.models.firebase.GameFirebase
import com.mibaldi.cah.data.models.Game

fun Game.toGameFirebase() : GameFirebase {
    return GameFirebase(
            nombre = name,
            config = GameConfigFirebase(numCardsByPlayer, numPlayers, rounds))
}

fun GameFirebase.toGame() : Game {
    return Game(
            name = nombre,
            numCardsByPlayer = config.numCartasJugador,
            numPlayers = config.numJugadores,
            rounds = config.rondas)
}