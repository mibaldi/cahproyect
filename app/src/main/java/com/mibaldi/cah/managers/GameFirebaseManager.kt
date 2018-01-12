package com.mibaldi.cah.managers

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mibaldi.cah.data.models.Game
import com.mibaldi.cah.data.models.Player
import javax.inject.Inject
import javax.inject.Singleton
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot



@Singleton
class GameFirebaseManager @Inject constructor(){

    var database = FirebaseDatabase.getInstance()
    companion object {
        val refPlayers = "jugadores"
        val refRounds = "turnos"
    }
    var gameRef = database.getReference("juegos")

    fun newGame(game : Game, res: (String) -> Unit){
        val key = gameRef.push().key
        gameRef.child(key).setValue(game.toGameFirebase())
        res(key)
    }

    fun addPlayer(key: String,player: Player) {
        gameRef.child(key).child("${refPlayers}/${player.username}").setValue(player.toPlayerFirebase())
    }

    fun startRound(gameKey: String) {
        gameRef.child(gameKey).child(refRounds).orderByKey().limitToLast(1).addListenerForSingleValueEvent (object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                gameRef.child(gameKey).child(refRounds).child(dataSnapshot.key).updateChildren(mapOf(Pair("estado",0)))
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //Handle possible errors.
            }
        })
    }

}


