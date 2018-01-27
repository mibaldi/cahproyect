package com.mibaldi.cah.data.models.firebase

data class GameConfigFirebase constructor(
        var numCartasJugador: Int = 2,
        var numJugadores: Int = 2,
        var rondas: Int = 2,
        var tiempo: Int = 30)