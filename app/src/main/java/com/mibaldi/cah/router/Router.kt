package com.mibaldi.cah.router

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mibaldi.cah.ui.activities.ConfigurationActivity
import com.mibaldi.cah.ui.activities.GameActivity
import com.mibaldi.cah.ui.activities.NewGameActivity
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.toast
import java.lang.ref.WeakReference
import javax.inject.Inject
import javax.inject.Singleton
import android.support.v4.app.ActivityCompat.startActivityForResult
import com.google.android.gms.appinvite.AppInviteInvitation
import com.mibaldi.cah.R
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.app.Activity.RESULT_OK
import com.mibaldi.cah.ui.activities.MainActivity


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

    fun goToNewGame() {
        with(applicationContext){
            startActivity(intentFor<NewGameActivity>().newTask())
        }
    }

    fun gotToGame(mKey: String) {
        with(applicationContext){
            startActivity(intentFor<GameActivity>("idGame" to mKey).newTask())
        }
    }

    fun goToMain() {
        with(applicationContext){
            startActivity(intentFor<MainActivity>().newTask())
        }
    }

    fun sharedWhatsapp(activity: GameActivity,mKey : String) {
        with(activity) {

            val intent = AppInviteInvitation.IntentBuilder("CAH")
                    .setMessage("Invitacion juego")
                    .setDeepLink(Uri.parse("http://mibaldi.com/$mKey"))
                    .setCustomImage(Uri.parse("https://developers.google.com/identity/images/g-logo.png"))
                    .setCallToActionText("Install!")
                    .build()
            startActivityForResult(intent,GameActivity.REQUEST_INVITE)


            /*val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.`package` = "com.whatsapp"
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, mKey)
            try {
                startActivity(whatsappIntent)
            } catch (ex: android.content.ActivityNotFoundException) {
                toast("Whatsapp have not been installed.").show()
            }*/
        }
    }
}