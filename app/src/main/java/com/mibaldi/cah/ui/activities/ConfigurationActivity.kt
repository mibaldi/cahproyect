package com.mibaldi.cah.ui.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import com.mibaldi.cah.R
import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.ui.presenters.configuration.ConfigurationPresenter
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.ConfigurationContract
import com.mibaldi.cah.utils.ViewModelFactory
import kotlinx.android.synthetic.main.actionbar_toolbar.*
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
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)
        model = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)

        mPresenter.initializer(model)

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
