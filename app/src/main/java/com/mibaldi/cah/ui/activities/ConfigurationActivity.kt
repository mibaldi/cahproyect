package com.mibaldi.cah.ui.activities

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.mibaldi.cah.R
import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.ui.presenters.configuration.ConfigurationPresenter
import com.mibaldi.cah.ui.views.ConfigurationContract
import kotlinx.android.synthetic.main.activity_configuration.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class ConfigurationActivity : BaseMvpActivity<ConfigurationContract.View,
        ConfigurationPresenter>(),
        ConfigurationContract.View {


    @Inject
    override lateinit var mPresenter :ConfigurationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)
        mPresenter.initializer()

        setupToolbar()
        btnChangeUsername.onClick { mPresenter.changeUsername(etUsername.text.toString()) }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }


    override fun showProgress() {
        progress.visibility = VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = GONE
    }

    override fun showError(message: String?) {
        message?.let {longSnackbar(llConfiguration,it)}
    }


}
