package com.mibaldi.cah.domain.interactors.main

interface  MainInteractor {

    fun getAllPlays()
    fun getCurrentPlayer(param: (Pair<String?,Error?>) -> Unit)
}