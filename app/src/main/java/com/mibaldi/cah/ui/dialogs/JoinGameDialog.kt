package com.mibaldi.cah.ui.dialogs

import android.app.Activity
import android.support.v7.app.AlertDialog
import com.mibaldi.cah.R
import kotlinx.android.synthetic.main.dialog_join_game.view.*

class JoinGameDialog {
    companion object {
        fun newInstance(activity: Activity, title:String="Introduce clave:", callback:(key:String)->Unit){
            val dialogBuilder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            val dialogView = inflater.inflate(R.layout.dialog_join_game, null)

            val dialog = dialogBuilder
                    .setView(dialogView)
                    .setTitle(title)
                    .setCancelable(false)
                    .setPositiveButton("OK", { dialog, whichButton ->
                        callback(dialogView.etJoinGame.text.toString())
                        dialog.dismiss()
                    })
                    .create()
            dialog.show()
        }
    }
}