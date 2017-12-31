package com.mibaldi.cah.data.repositories

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor() {

    fun getAllPlays(){
        Log.d("REPOSITORY","PlayList")
    }

}