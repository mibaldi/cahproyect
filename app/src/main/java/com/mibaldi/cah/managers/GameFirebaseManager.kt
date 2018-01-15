package com.mibaldi.cah.managers

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mibaldi.cah.data.models.Game
import com.mibaldi.cah.data.models.Player
import javax.inject.Inject
import javax.inject.Singleton
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.mibaldi.cah.data.models.firebase.PlayerFirebase
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
                        this.isGamePrepared(key,subscriber)
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
    fun whoIsRoundPlayer(gameKey: String,subscriber: Observer<String>){
        gameRef.child(gameKey).child(refRounds).orderByKey().limitToLast(1).addValueEventListener (object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    gameRef.child(gameKey).child(refRounds).orderByKey().limitToLast(1).removeEventListener(this)
                    val narrator = it.child("narrador").value as String
                    subscriber.onNext(narrator)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //Handle possible errors.
            }
        })
    }

    fun getNumPlayers(gameKey: String,subscriber: Observer<Long>){
        gameRef.child(gameKey).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var numPlayers = dataSnapshot.child("jugadores").childrenCount

                subscriber.onNext(numPlayers)

            }
            override fun onCancelled(databaseError: DatabaseError) {
                subscriber.onError(Error(databaseError.message))
            }
        })
    }

    fun isGamePrepared(gameKey: String,subscriber: Observer<String>){
        gameRef.child(gameKey).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                when(dataSnapshot.child("estado").value){
                    1L -> {
                        gameRef.child(gameKey).removeEventListener(this)
                        subscriber.onNext(gameKey)
                        subscriber.onComplete()
                    }
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


