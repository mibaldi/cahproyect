package com.mibaldi.cah.data.parsers

import com.mibaldi.cah.data.models.Card
import com.mibaldi.cah.data.models.firebase.CardFirebase

fun List<Card>.toCardFirebaseRefList() : List<Int> = this.map { it.id }