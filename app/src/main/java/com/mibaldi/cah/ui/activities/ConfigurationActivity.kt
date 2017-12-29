package com.mibaldi.cah.ui.activities

import android.os.Bundle
import com.mibaldi.cah.R
import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.ui.presenters.configuration.ConfigurationModule
import com.mibaldi.cah.ui.presenters.configuration.ConfigurationPresenter
import com.mibaldi.cah.ui.views.ConfigurationContract
import com.mibaldi.cah.utils.app

class ConfigurationActivity : BaseMvpActivity<ConfigurationContract.View,
        ConfigurationPresenter>(),
        ConfigurationContract.View {
    override var mPresenter = ConfigurationPresenter()
    val component by lazy { app.component.plus(ConfigurationModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)
        component.inject(this)
        mPresenter.initializer()
    }
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }

}
