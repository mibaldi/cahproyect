package com.mibaldi.cah.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.mibaldi.cah.R
import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.ui.dialogs.JoinGameDialog
import com.mibaldi.cah.ui.presenters.main.MainPresenter
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.MainContract
import com.mibaldi.cah.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_contain.*
import org.jetbrains.anko.design.longSnackbar


import javax.inject.Inject
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.google.firebase.appinvite.FirebaseAppInvite
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.mibaldi.cah.data.models.uimodels.Game
import com.mibaldi.cah.ui.adapters.GameListAdapter


class MainActivity : BaseMvpActivity<MainContract.View,
        MainPresenter>(),
        MainContract.View, NavigationView.OnNavigationItemSelectedListener {



    @Inject
    override lateinit var mPresenter: MainPresenter
    @Inject
    lateinit var viewModelFactory:ViewModelFactory
    lateinit var model: MainViewModel
    lateinit var adapter: GameListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        model = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)
        mPresenter.initializer(model)
        mPresenter.getGameList()
        setupToolbar()
        invites()
        rvGameList.setHasFixedSize(true);
        val mLayoutManager = LinearLayoutManager(this);
        rvGameList.layoutManager = mLayoutManager;
        adapter = GameListAdapter()

        rvGameList.adapter = adapter
    }


    private fun invites() {
        FirebaseDynamicLinks.getInstance().getDynamicLink(intent)
                .addOnSuccessListener(this, OnSuccessListener { data ->
                    if (data == null) {
                        Log.d(BaseMvpActivity.TAG, "getInvitation: no data")
                        return@OnSuccessListener
                    }
                    // Get the deep link
                    val deepLink = data.link
                    // Extract invite
                    val invite = FirebaseAppInvite.getInvitation(data)
                    if (invite != null) {
                        val invitationId = invite.invitationId
                    }
                    // Handle the deep link
                    // [START_EXCLUDE]
                    Log.d(BaseMvpActivity.TAG, "deepLink:" + deepLink!!)
                    mPresenter.joinGame(deepLink.toString().split(".com/")[1])
                    /* val intent = Intent(Intent.ACTION_VIEW)
                        intent.`package` = packageName
                        intent.data = deepLink

                        startActivity(intent)*/
                    // [END_EXCLUDE]
                })
                .addOnFailureListener(this) { e -> Log.w(BaseMvpActivity.TAG, "getDynamicLink:onFailure", e) }
    }

    override fun showCurrentUser(user: String) {
        tvCurrentUser.text=user
    }
    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        nav_view.setNavigationItemSelectedListener(this)
    }
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                mPresenter.goToConfiguration()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_newGame -> { mPresenter.goToNewGame()}
            R.id.nav_joinGame -> { mPresenter.showJoinGameAlert()
            }
            /*R.id.nav_ownworkstation -> { mPresenter.goToManageOwnWorkstation() }
            R.id.nav_workstationlist -> { mPresenter.goToFillWorkstation() }
            R.id.nav_share -> {}
            R.id.nav_send -> {}*/
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun showError(message: String?) {
        longSnackbar(llMain,message!!)
    }
    override fun observeUser(observer: Observer<String>) {
        model.currentUser.observe(this,observer)
    }

    override fun alertJoinGame() {
        JoinGameDialog.newInstance(this,"Introduce clave:"){
            mKey -> mPresenter.joinGame(mKey)
        }
    }

    override fun observeList(observerGameList: Observer<List<Game>>) {
        model.gameList.observe(this,observerGameList)
    }

    override fun showGameList(listGame: List<Game>) {

        adapter.setDataAndListener(listGame,object : GameListAdapter.OnItemClickListener{
            override fun onItemClickListener(game: Game) {
                mPresenter.joinGame(game.name)
            }

        })
    }
}
