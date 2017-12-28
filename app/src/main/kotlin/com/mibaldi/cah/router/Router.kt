package com.mibaldi.cah.router

import android.content.Context
import android.content.Intent
import android.util.Log
import com.mibaldi.cah.ui.activities.MainActivity
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity

class Router(val applicationContext:Context){
    fun goToConfiguration() {
        with(applicationContext){
            //TODO cambiar actividad
            startActivity(intentFor<MainActivity>().newTask())
        }
    }

}