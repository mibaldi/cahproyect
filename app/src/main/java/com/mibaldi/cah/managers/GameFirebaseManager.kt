package com.mibaldi.cah.managers

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mibaldi.cah.data.models.Game
import com.mibaldi.cah.data.models.Player
import javax.inject.Inject
import javax.inject.Singleton
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import io.reactivex.Observable
import io.reactivex.Observer


@Singleton
class GameFirebaseManager @Inject constructor(){


    companion object {
        var database = FirebaseDatabase.getInstance()
        val refPlayers = "jugadores"
        val refRounds = "turnos"
        var gameRef = database.getReference("juegos")
    }


    fun newGame(game: Game, subscriber: Observer<String>){
        val key = gameRef.push().key
        gameRef.child(key).setValue(game.toGameFirebase())
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        gameRef.child("$key/estado").addValueEventListener(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                when(dataSnapshot.value){
                                    1L -> {
                                        gameRef.child("$key/estado").removeEventListener(this)
                                        subscriber.onNext(key)
                                        subscriber.onComplete()
                                    }
                                    else -> subscriber.onError(Error("Juego no preparado"))
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {
                                subscriber.onError(Error("Juego no preparado"))
                            }
                        })
                    }
                }
    }

    fun addPlayer(key: String,player: Player,subscriber: Observer<Boolean>) {
        gameRef.child(key).child("$refPlayers/${player.username}").setValue(player.toPlayerFirebase())
                .addOnCompleteListener{ task ->
                    subscriber.onNext(task.isSuccessful)
                }
    }

    fun startRound(gameKey: String) {
        gameRef.child(gameKey).child(refRounds).orderByKey().limitToLast(1).addListenerForSingleValueEvent (object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    gameRef.child(gameKey).child(refRounds).child(it.key).child("estado").setValue(0)
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                //Handle possible errors.
            }
        })
    }
    fun isGamePrepared(gameKey: String,subscriber: Observer<Boolean>){
        gameRef.child(gameKey).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                when(dataSnapshot.child("estado").value){
                    3L -> {
                        gameRef.child(gameKey).removeEventListener(this)
                        subscriber.onComplete()
                    }
                    else -> subscriber.onError(Error("Juego no preparado"))
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                subscriber.onError(Error(databaseError.message))
            }
        })
    }

}


