package com.mibaldi.cah.managers

import android.content.Context
import com.securepreferences.SecurePreferences
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPreferencesManager @Inject constructor(val context : Context){


    companion object {
        var USERNAME = "USERNAME"
    }

    private val prefs: SecurePreferences by lazy {
        SecurePreferences(context)
    }

    var username: String?
        get() = prefs.getString(USERNAME,null)
        set(value) = prefs.edit().putString(USERNAME,value).apply()

}