package com.mibaldi.cah.data.models.firebase

import com.mibaldi.cah.data.models.Turn

data class TurnFirebase constructor(var narrador : String, var estado : Long?, var pregunta : String?, var ganador : String?, var posibles : List<Pair<String,String>>?) {
    fun toTurn() = Turn(null,narrador,estado,pregunta,posibles,ganador)
}