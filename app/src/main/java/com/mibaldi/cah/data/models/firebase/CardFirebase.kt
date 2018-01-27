package com.mibaldi.cah.data.models.firebase

import com.mibaldi.cah.data.models.uimodels.Card

data class CardFirebase(var id : Int = 0, var sentence :String = "") {
    fun toCard() = Card(id, sentence)
}