package com.mibaldi.cah.data.models.firebase

import com.mibaldi.cah.data.models.Game

data class GameFirebase constructor(var nombre : String, var config : GameConfigFirebase) {

    constructor() : this("", GameConfigFirebase(0,0,0))

    fun toGame() = Game(nombre, "" ,config.numCartasJugador, config.numJugadores, config.rondas)
}