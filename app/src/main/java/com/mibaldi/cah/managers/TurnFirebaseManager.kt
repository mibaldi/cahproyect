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
class TurnFirebaseManager @Inject constructor(){

    companion object {
        var database = FirebaseDatabase.getInstance()
        val refPlayers = "jugadores"
        val refRounds = "turnos"
        var gameRef = database.getReference("juegos")
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

    //TODO devolver turno entero
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

    //TODO devolver turno entero
    fun stateOfTurn(gameKey: String,subscriber: Observer<String>){
        gameRef.child(gameKey).child(refRounds).orderByKey().limitToLast(1).addValueEventListener (object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    val state = it.child("estado").value
                    if (state != null){
                        val stateLong = state as Long
                        subscriber.onNext(stateLong.toString())
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //Handle possible errors.
            }
        })
    }
    fun getQuestions(gameKey: String,subscriber: Observer<List<Long>>){
        gameRef.child(gameKey).child("cartas/negras").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val list = mutableListOf<Long>()
                dataSnapshot.children.forEach({
                    list.add(it.value as Long)
                })
                subscriber.onNext(list)
                subscriber.onComplete()
            }

        })
    }

    //TODO hacer el set sobre el turno en vez de consultar el ultimo agregado Coger el valor del currentTurn
    fun setQuestion(gameKey: String,idQuestion: Long,subscriber: Observer<Boolean>){
        gameRef.child(gameKey).child(refRounds).orderByKey().limitToLast(1).addListenerForSingleValueEvent (object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {

                    it.child("pregunta").ref.setValue(idQuestion)
                            .addOnCompleteListener {
                                subscriber.onNext(true)
                                subscriber.onComplete()
                            }.addOnFailureListener {
                                subscriber.onError(it)
                            }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //Handle possible errors.
            }
        })
    }
}


