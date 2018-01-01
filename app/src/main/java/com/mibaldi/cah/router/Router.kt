package com.mibaldi.cah.router

import android.content.Context
import com.mibaldi.cah.ui.activities.ConfigurationActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Router @Inject constructor(val applicationContext:Context){

    fun goToConfiguration() {
        with(applicationContext){
            startActivity(intentFor<ConfigurationActivity>().newTask())
        }
    }
    fun  closeActivity(weakReference: WeakReference<ConfigurationActivity>) {
        weakReference.get()?.finish()
    }
}