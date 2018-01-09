package com.mibaldi.cah.data.repositories

import android.util.Log
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.data.models.Game
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor() {

    fun getAllGames(){
        Log.d("REPOSITORY","PlayList")
    }
}