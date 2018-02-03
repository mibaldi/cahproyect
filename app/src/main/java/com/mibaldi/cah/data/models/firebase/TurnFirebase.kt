package com.mibaldi.cah.data.models.firebase

import com.mibaldi.cah.data.models.uimodels.Answer
import com.mibaldi.cah.data.models.uimodels.Question
import com.mibaldi.cah.data.models.uimodels.Turn

data class TurnFirebase constructor(var narrador : String = "", var estado : Long? = null, var pregunta : Long? = null, var ganador : String? = null, var posibles : List<Pair<String,Long>>? = null) {
    fun toTurn(question: Question? = null,answerList: List<Answer>? = null) = Turn(null, narrador, estado, null, null, ganador)
}