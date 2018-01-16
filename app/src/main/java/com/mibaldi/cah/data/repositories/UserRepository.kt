package com.mibaldi.cah.data.repositories

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor() {

    fun changeUsername(username: String, param: (Pair<String?,Error?>) -> Unit){
        Log.d("REPOSITORY","Changed")
        param(Pair(username,null))
        /*if(username.equals("Mikel")){
            param(Pair(username,null))
        }else {
            param(Pair(null,Error("Username no valido")))
        }*/

    }

}