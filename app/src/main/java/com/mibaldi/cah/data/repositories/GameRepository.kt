package com.mibaldi.cah.data.repositories

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mibaldi.cah.data.models.uimodels.Game
import com.mibaldi.cah.data.models.firebase.GameFirebase
import io.reactivex.Observer
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class GameRepository @Inject constructor() {
    companion object {
        val database = FirebaseDatabase.getInstance()
        val gamesRef =  database.reference.child("juegos")
    }
    fun getAllGames(subscriber: Observer<List<Game>>){
        val games: MutableList<Game> = mutableListOf()
        gamesRef.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError?) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataSnapshot.children.mapNotNullTo(games) {
                    val firebaseGame = it.getValue<GameFirebase>(GameFirebase::class.java)
                    val game = firebaseGame?.toGame()
                    game?.keyGame = dataSnapshot.key
                    game
                }

                subscriber.onNext(games)
                subscriber.onComplete()
            }

        })
        Log.d("REPOSITORY","PlayList")
    }
}