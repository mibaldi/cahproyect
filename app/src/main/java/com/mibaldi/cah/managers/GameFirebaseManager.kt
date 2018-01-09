package com.mibaldi.cah.managers

import com.google.firebase.database.FirebaseDatabase
import com.mibaldi.cah.data.models.Game
import com.mibaldi.cah.data.parsers.toGameFirebase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameFirebaseManager @Inject constructor(){

    var database = FirebaseDatabase.getInstance()
    var gameRef = database.getReference("juegos")

    fun newGame(game : Game, res: () -> Unit){
        val key = gameRef.push().key
        gameRef.child(key).setValue(game.toGameFirebase())
        res()
    }

}


