package com.mibaldi.cah.managers

import com.google.firebase.database.*
import com.mibaldi.cah.data.models.firebase.TurnFirebase
import com.mibaldi.cah.data.models.uimodels.*
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton
import io.reactivex.Observer
import io.reactivex.rxkotlin.merge



@Singleton
class GameFirebaseManager @Inject constructor(){


    companion object {
        var database = FirebaseDatabase.getInstance()
        val blackCards = database.getReference("cartas/negras")
        val whiteCards = database.getReference("cartas/blancas")
        val refPlayers = "jugadores"
        val refRounds = "turnos"
        var gameRef = database.getReference("juegos")
    }


    fun newGame(game: Game, subscriber: Observer<String>){
        val key = gameRef.push().key
        gameRef.child(key).setValue(game.toGameFirebase())
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        this.checkGameStatus(key,subscriber)
                    }
                }
    }

    fun addPlayer(key: String, player: Player, subscriber: Observer<Boolean>) {
        gameRef.child(key).child("$refPlayers/${player.username}").setValue(player.toPlayerFirebase())
                .addOnCompleteListener{ task ->
                    subscriber.onNext(task.isSuccessful)
                }
    }

    fun startGame(gameKey: String){
        gameRef.child(gameKey).addListenerForSingleValueEvent (object : ValueEventListener {
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
    fun whoIsRoundPlayer(gameKey: String,subscriber: Observer<Pair<String,Long>>){
        gameRef.child(gameKey).child(refRounds).orderByKey().limitToLast(1).addValueEventListener (object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataSnapshot.children.forEach {
                    //gameRef.child(gameKey).child(refRounds).orderByKey().limitToLast(1).removeEventListener(this)
                    val narrator = it.child("narrador").value as String
                    val pair = Pair(narrator, it.key.toLong())
                    subscriber.onNext(pair)
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                //Handle possible errors.
            }
        })
    }

    fun getNumPlayers(game : Game, subscriber: Observer<Long>){
        gameRef.child(game.keyGame).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var numPlayers = dataSnapshot.child("jugadores").childrenCount

                subscriber.onNext(numPlayers)
                if(game.numPlayers == numPlayers.toInt()){
                    subscriber.onComplete()
                }

            }
            override fun onCancelled(databaseError: DatabaseError) {
                subscriber.onError(Error(databaseError.message))
            }
        })
    }

    fun checkGameStatus(gameKey: String, subscriber: Observer<String>){
        gameRef.child(gameKey).child("estado").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                when(dataSnapshot.value){
                    1L -> {
                        gameRef.child(gameKey).child("estado").removeEventListener(this)
                        subscriber.onNext(gameKey)
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

    //TODO devolver turno entero
    fun stateOfTurn(gameKey: String,subscriber: Observer<Turn>){
        var currentState = -1L
        gameRef.child(gameKey).child(refRounds).orderByKey().limitToLast(1).addChildEventListener(object : ChildEventListener{
            override fun onCancelled(p0: DatabaseError?) {}

            override fun onChildMoved(p0: DataSnapshot?, p1: String?) {}

            override fun onChildChanged(child: DataSnapshot?, p1: String?) {
                child?.let {
                    val turnFirebase = it.getValue<TurnFirebase>(TurnFirebase::class.java)
                    val turnKey = it.key
                    turnFirebase?.let {
                        val turn = turnFirebase.toTurn()
                        with(it) {
                            estado?.let {
                                if (currentState != it) {
                                    turn.turnNumber = turnKey
                                    subscriber.onNext(turn)
                                }
                            }
                          /*  pregunta?.let {
                                getQuestion(it).map { turn.question }
                            }*/
                        }
                    }
                }
            }

            override fun onChildAdded(child: DataSnapshot?, p1: String?) {
                child?.let {
                    val turnFirebase = it.getValue<TurnFirebase>(TurnFirebase::class.java)
                    val turnKey = it.key
                    turnFirebase?.estado?.let{
                        currentState =it
                        val turn = turnFirebase.toTurn()
                        turn.turnNumber = turnKey
                        subscriber.onNext(turn)
                    }
                }

            }

            override fun onChildRemoved(p0: DataSnapshot?) {}
        })
    }
    fun getQuestion(idQuestion:Long):Observable<Question>{
       return Observable.create<Question>{
            val emmiter = it
            blackCards.child(idQuestion.toString()).addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot?) {
                    dataSnapshot?.let {
                        val question = Question(idQuestion, it.value as String)
                        emmiter.onNext(question)
                        emmiter.onComplete()
                    }.also { emmiter.onError(Error("vacio")) }
                }
                override fun onCancelled(p0: DatabaseError?) {
                    emmiter.onError(Error(p0?.message))
                }
            })
        }

    }
    fun getAnswer(idAnswer:Long,userId:String):Observable<Answer>{
       return Observable.create<Answer>{
            val emitter = it
            blackCards.child(idAnswer.toString()).addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot?) {
                    dataSnapshot?.let {
                        val answer = Answer(userId,idAnswer,it.value as String)
                        emitter.onNext(answer)
                        emitter.onComplete()
                    }.also { emitter.onError(Error("vacio")) }
                }
                override fun onCancelled(p0: DatabaseError?) {
                    emitter.onError(Error(p0?.message))
                }
            })
        }
    }
    fun getListAnswer(pairList: List<Pair<Long,String>>):Observable<Answer>{
        val observableList = mutableListOf<Observable<Answer>>()
        for (pair in pairList){
            observableList.add(getAnswer(pair.first,pair.second))
        }
        return observableList.merge()

    }
}


