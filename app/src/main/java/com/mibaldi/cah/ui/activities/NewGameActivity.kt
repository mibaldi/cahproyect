package com.mibaldi.cah.ui.activities

import android.os.Bundle
import android.view.View
import com.mibaldi.cah.R
import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.ui.presenters.newGame.NewGamePresenter
import com.mibaldi.cah.ui.views.NewGameContract
import kotlinx.android.synthetic.main.activity_newgame.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class NewGameActivity : BaseMvpActivity<NewGameContract.View,
        NewGamePresenter>(),
        NewGameContract.View {

    @Inject
    override lateinit var mPresenter : NewGamePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newgame)
        setupToolbar()
        btn_newGame.onClick { mPresenter.createGame()}
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }


    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showError(message: String?) {
        message?.let { longSnackbar(llConfiguration, it) }
    }

    override fun showSharedAlert() {
        alert("Juego creado") {
            okButton {
                mPresenter.goToGameActivity()
            }
        }.show()
    }

}