package com.mibaldi.cah.ui.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.mibaldi.cah.R
import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.ui.presenters.main.MainModule
import com.mibaldi.cah.ui.presenters.main.MainPresenter
import com.mibaldi.cah.ui.views.MainContract
import com.mibaldi.cah.utils.app
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_contain.*


class MainActivity : BaseMvpActivity<MainContract.View,
        MainPresenter>(),
        MainContract.View, NavigationView.OnNavigationItemSelectedListener {


    override var mPresenter: MainPresenter = MainPresenter()
    val component by lazy { app.component.plus(MainModule(this)) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)
        mPresenter.initializer()
        setupToolbar()
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
            /*R.id.nav_ownworkstation -> { mPresenter.goToManageOwnWorkstation() }
            R.id.nav_workstationlist -> { mPresenter.goToFillWorkstation() }
            R.id.nav_share -> {}
            R.id.nav_send -> {}*/
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
