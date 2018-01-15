package com.mibaldi.cah.ui.presenters.game

import android.util.Log
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.data.models.Game
import com.mibaldi.cah.data.models.Player
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.activities.NewGameActivity
import com.mibaldi.cah.ui.views.NewGameContract
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import javax.inject.Inject
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.mibaldi.cah.ui.views.GameContract
import org.jetbrains.anko.toast


class GamePresenter @Inject constructor(val router: Router, val gameManager: GameFirebaseManager): BasePresenter<GameContract.View>(), GameContract.Presenter {


    override fun initialize() {

    }

    /*fun sharedWhatsapp(){
        val whatsappIntent = Intent(Intent.ACTION_SEND)
        whatsappIntent.type = "text/plain"
        whatsappIntent.`package` = "com.whatsapp"
        whatsappIntent.putExtra(Intent.EXTRA_TEXT, mKey)
        try {
            activity.startActivity(whatsappIntent)
        } catch (ex: android.content.ActivityNotFoundException) {
            activity.toast("Whatsapp have not been installed.").show()
        }

    }*/

}