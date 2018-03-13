package com.mibaldi.cah.ui.activities

import android.app.Activity
import android.app.ProgressDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.gms.appinvite.AppInviteInvitation
import com.mibaldi.cah.R
import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.ui.fragments.GameFragmentQuestion
import com.mibaldi.cah.ui.fragments.GameFragmentResponses
import com.mibaldi.cah.ui.fragments.GameFragmentResult
import com.mibaldi.cah.ui.fragments.GameFragmentWinner
import com.mibaldi.cah.ui.presenters.game.GamePresenter
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.GameContract
import com.mibaldi.cah.utils.ViewModelFactory
import com.mibaldi.cah.utils.addFragment
import com.mibaldi.cah.utils.replaceFragment
import kotlinx.android.synthetic.main.actionbar_toolbar.*
import kotlinx.android.synthetic.main.activity_game.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.progressDialog
import org.jetbrains.anko.toast
import javax.inject.Inject

class GameActivity : BaseMvpActivity<GameContract.View,
        GamePresenter>(),
        GameContract.View {

    companion object {
        val REQUEST_INVITE = 209
    }
    @Inject
    override lateinit var mPresenter : GamePresenter
    lateinit var idGame: String

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var model: MainViewModel
    private var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        invites()
        setupToolbar()
        model = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)

        mPresenter.initialize(model)
        btnInitGame.setOnClickListener{ mPresenter.startRound() }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    private fun invites() {

        val appLinkData = intent.data

        if(appLinkData != null){
            val uriString = appLinkData.toString()
            val uri = Uri.parse(uriString)

            val gameKey = uri.getQueryParameter("key")
            toast(gameKey).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.game, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_invite -> {
                mPresenter.invitePlayers()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun showProgress() {
        dialog = indeterminateProgressDialog("Iniciando partida. Espere...")
    }

    override fun hideProgress() {
        dialog?.dismiss()
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
    override fun showTurn(turn: Long) {
        tvTurn.text = "Es el turno $turn"
    }

    override fun changeState(idGame: String,state: String) {
        when(state){
            "0" -> replaceFragment(GameFragmentQuestion.newInstance(idGame,"Narrator"),R.id.flGameFragment)
            "1" -> replaceFragment(GameFragmentResponses.newInstance(idGame,"Responses"),R.id.flGameFragment)
            "2" -> replaceFragment(GameFragmentResult.newInstance(idGame,"Results"),R.id.flGameFragment)
            "3" -> replaceFragment(GameFragmentWinner.newInstance(idGame,"Winner"),R.id.flGameFragment)
        }
    }


}