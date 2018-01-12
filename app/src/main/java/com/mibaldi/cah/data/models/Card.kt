package com.mibaldi.cah.data.models

import com.mibaldi.cah.data.models.firebase.CardFirebase

data class Card (var id : Int, var sentence :String = ""){
    fun toCardFirebase() = CardFirebase(id,sentence)
}