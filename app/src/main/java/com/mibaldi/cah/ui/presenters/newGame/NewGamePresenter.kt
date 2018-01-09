package com.mibaldi.cah.ui.presenters.newGame

import android.util.Log
import com.mibaldi.cah.base.presenters.activities.BasePresenter
import com.mibaldi.cah.data.models.Game
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.views.NewGameContract
import javax.inject.Inject

class NewGamePresenter @Inject constructor(val router: Router, val gameManager: GameFirebaseManager): BasePresenter<NewGameContract.View>(), NewGameContract.Presenter {

    override fun createGame() {

        gameManager.newGame(Game("miJuego")){
            Log.d("NewGamePresenter","CREADO")
        }
    }

}