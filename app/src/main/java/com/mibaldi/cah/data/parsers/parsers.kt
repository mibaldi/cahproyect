package com.mibaldi.cah.data.parsers

import com.mibaldi.cah.data.models.uimodels.Card

fun List<Card>.toCardFirebaseRefList() : List<Int> = this.map { it.id }