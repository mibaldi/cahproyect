package com.mibaldi.cah.data.models.firebase

import com.mibaldi.cah.data.models.Game

data class GameFirebase constructor(var nombre : String, var config : GameConfigFirebase) {
    fun toGame() = Game(nombre, config.numCartasJugador, config.numJugadores, config.rondas)
}