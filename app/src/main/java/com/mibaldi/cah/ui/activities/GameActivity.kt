package com.mibaldi.cah.ui.activities

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.gms.appinvite.AppInviteInvitation
import com.mibaldi.cah.R
import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.ui.fragments.GameFragmentQuestion
import com.mibaldi.cah.ui.fragments.GameFragmentResponses
import com.mibaldi.cah.ui.presenters.game.GamePresenter
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.GameContract
import com.mibaldi.cah.utils.ViewModelFactory
import com.mibaldi.cah.utils.addFragment
import com.mibaldi.cah.utils.replaceFragment
import kotlinx.android.synthetic.main.activity_game.*
import org.jetbrains.anko.design.longSnackbar
import javax.inject.Inject

class GameActivity : BaseMvpActivity<GameContract.View,
        GamePresenter>(),
        GameContract.View {


    companion object {
        val REQUEST_INVITE = 209
    }
    @Inject
    override lateinit var mPresenter : GamePresenter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var model: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        setupToolbar()
        val idGame = intent.getStringExtra("idGame")
        model = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)
        mPresenter.initialize(idGame,model)
        btnShared.setOnClickListener { mPresenter.sharedWhatsapp() }
        btnInitGame.setOnClickListener{ mPresenter.changeStateRound() }
        addFragment(GameFragmentQuestion.newInstance(idGame,"Narrator"),R.id.flGameFragment)
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

    override fun showButton() {
        btnInitGame.visibility = View.VISIBLE
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult: requestCode=$requestCode, resultCode=$resultCode")


        if (requestCode == REQUEST_INVITE) {
            if (resultCode == Activity.RESULT_OK) {
                // Get the invitation IDs of all sent messages
                val ids = AppInviteInvitation.getInvitationIds(resultCode, data)
                for (id in ids) {
                    Log.d(TAG, "onActivityResult: sent invitation " + id)
                }
            } else {
                // Sending failed or it was canceled, show failure message to the user
                // ...
            }
        }
    }

    override fun changeState(state: String) {
        when(state){
            "0" -> replaceFragment(GameFragmentQuestion.newInstance("","Narrator"),R.id.flGameFragment)
            "1" -> replaceFragment(GameFragmentResponses.newInstance("","Responses"),R.id.flGameFragment)
            "2" -> replaceFragment(GameFragmentResponses.newInstance("","Responses2"),R.id.flGameFragment)
            "3" -> replaceFragment(GameFragmentResponses.newInstance("","Responses3"),R.id.flGameFragment)
        }
    }


}