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


    fun newGame(game : Game) : Observable<String>{
        val key = gameRef.push().key
        gameRef.child(key).setValue(game.toGameFirebase())
        return Observable.create<String> {
            it.onNext(key)
            it.onComplete()
        }
    }

    fun addPlayer(key: String,player: Player,subscriber: Observer<Boolean>) {
        gameRef.child(key).child("${refPlayers}/${player.username}").setValue(player.toPlayerFirebase())
                .addOnCompleteListener{ task ->
                    subscriber.onNext(task.isSuccessful)
                }
    }

    fun startRound(gameKey: String) {
        gameRef.child(gameKey).child(refRounds).orderByKey().limitToLast(1).addListenerForSingleValueEvent (object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    gameRef.child(gameKey).child(refRounds).child(it.key).child("estado").setValue(1)
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
                    1L -> {
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


