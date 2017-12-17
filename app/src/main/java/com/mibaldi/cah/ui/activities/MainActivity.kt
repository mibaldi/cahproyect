package com.mibaldi.cah.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mibaldi.cah.R
import com.mibaldi.cah.base.BaseMvpActivity
import com.mibaldi.cah.ui.presenters.MainModule
import com.mibaldi.cah.ui.presenters.MainPresenter
import com.mibaldi.cah.ui.views.MainContract
import com.mibaldi.cah.utils.app
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainContract.View,
        MainPresenter>(),
        MainContract.View {


    override var mPresenter: MainPresenter = MainPresenter(this)
    val component by lazy { app.component.plus(MainModule(this)) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)
        mPresenter.init()
    }
    override fun showCurrentUser(user: String) {
        tvCurrentUser.text=user
    }
}
