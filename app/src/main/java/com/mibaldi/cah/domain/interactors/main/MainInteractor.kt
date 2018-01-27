package com.mibaldi.cah.domain.interactors.main

import com.mibaldi.cah.data.models.uimodels.Game
import io.reactivex.Observer

interface  MainInteractor {

    fun getAllPlays(subscriber: Observer<List<Game>>)
    fun getCurrentPlayer(param: (Pair<String?,Error?>) -> Unit)
}