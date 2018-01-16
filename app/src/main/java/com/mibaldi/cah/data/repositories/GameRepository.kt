package com.mibaldi.cah.data.repositories

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.mibaldi.cah.data.models.firebase.GameConfigFirebase
import com.mibaldi.cah.data.models.firebase.GameFirebase
import io.reactivex.Observer
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class GameRepository @Inject constructor() {
    companion object {
        val database = FirebaseDatabase.getInstance()
        val gamesRef =  database.reference.child("juegos")
    }
    fun getAllGames(subscriber: Observer<GameFirebase>){
        gamesRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError?) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (children in dataSnapshot.getChildren()) {

                    //val game = children.getValue(GameFirebase::class.java)

                    val config = children.child("config")
                    val numCartasJugador = config.child("numCartasJugador").value as Long
                    val numJugadores = config.child("numJugadores").value as Long
                    val rondas = config.child("rondas").value as Long
                    val tiempo = config.child("tiempo").value as Long
                    val gameConfigFirebase = GameConfigFirebase(numCartasJugador.toInt(), numJugadores.toInt(), rondas.toInt(), tiempo.toInt())
                    val game = GameFirebase(children.key,gameConfigFirebase )
                    game.let {
                        subscriber.onNext(it)
                    }
                }
                subscriber.onComplete()
            }

        })
        Log.d("REPOSITORY","PlayList")
    }
}