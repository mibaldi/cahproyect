package com.mibaldi.cah.data.models.firebase

data class GameConfigFirebase constructor(
        var numCartasJugador: Int,
        var numJugadores: Int,
        var rondas: Int,
        var tiempo: Int = 30)