package com.mibaldi.cah.router

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mibaldi.cah.ui.activities.ConfigurationActivity
import com.mibaldi.cah.ui.activities.GameActivity
import com.mibaldi.cah.ui.activities.NewGameActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Router @Inject constructor(val applicationContext: Context) {

    fun goToConfiguration() {
        with(applicationContext) {
            startActivity(intentFor<ConfigurationActivity>().newTask())
        }
    }

    fun closeActivity(weakReference: WeakReference<ConfigurationActivity>) {
        weakReference.get()?.finish()
    }

    fun goToNewGame() {
        with(applicationContext) {
            startActivity(intentFor<NewGameActivity>().newTask())
        }
    }

    fun gotToGame(mKey: String) {
        with(applicationContext) {
            startActivity(intentFor<GameActivity>("idGame" to mKey).newTask())
        }
    }

    fun inviteGame(activity: GameActivity, deepLink: Uri) {
        with(activity) {
            val msg = "Te invito a jugar: $deepLink"

            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            emailIntent.putExtra(Intent.EXTRA_TEXT, msg)

            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.`package` = "com.whatsapp"
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, msg)

            val uri = Uri.parse("smsto:")
            val smsIntent = Intent(Intent.ACTION_SENDTO, uri)
            smsIntent.putExtra("sms_body", msg)

            val chooserIntent = Intent.createChooser(Intent(), "Elige una opción")
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(whatsappIntent, emailIntent, smsIntent))


            startActivity(chooserIntent)

        }
    }


}