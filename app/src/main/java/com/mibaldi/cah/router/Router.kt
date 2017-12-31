package com.mibaldi.cah.router

import android.content.Context
import android.support.v7.app.AppCompatActivity
import com.mibaldi.cah.ui.activities.ConfigurationActivity
import dagger.android.DaggerActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Router @Inject constructor(val applicationContext:Context){

    fun goToConfiguration() {
        with(applicationContext){
            startActivity(intentFor<ConfigurationActivity>().newTask())
        }
    }
    fun closeActivity(activity: AppCompatActivity){
        activity.finish()
    }

}