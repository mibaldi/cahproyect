package com.mibaldi.cah.data.models.firebase

import com.mibaldi.cah.data.models.Card

data class CardFirebase(var id : Int, var sentence :String = "") {
    fun toCard() = Card(id,sentence)
}