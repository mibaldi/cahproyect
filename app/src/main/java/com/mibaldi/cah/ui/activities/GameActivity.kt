package com.mibaldi.cah.ui.activities

import android.os.Bundle
import android.view.View
import com.mibaldi.cah.R
import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.ui.presenters.game.GamePresenter
import com.mibaldi.cah.ui.views.GameContract
import kotlinx.android.synthetic.main.activity_game.*
import org.jetbrains.anko.design.longSnackbar
import javax.inject.Inject

class GameActivity : BaseMvpActivity<GameContract.View,
        GamePresenter>(),
        GameContract.View {

    @Inject
    override lateinit var mPresenter : GamePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        setupToolbar()
        val idGame = intent.getStringExtra("idGame")
        mPresenter.initialize(idGame)
        btnShared.setOnClickListener { mPresenter.sharedWhatsapp() }

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

    override fun changeNumPlayers(it: Long) {
        tvCurrentPlayers.text = "$it jugadores"
    }



}