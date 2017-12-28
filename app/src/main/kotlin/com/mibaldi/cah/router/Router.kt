package com.mibaldi.cah.router

import android.content.Context
import android.content.Intent
import com.mibaldi.cah.ui.activities.MainActivity
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class Router(){

    @Inject lateinit var mContext: Context
    fun goToConfiguration() {
        with(mContext){
            //TODO cambiar actividad
            startActivity(intentFor<MainActivity>().newTask())
        }
    }

}