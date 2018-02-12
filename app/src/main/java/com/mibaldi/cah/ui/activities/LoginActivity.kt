package com.mibaldi.cah.ui.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.mibaldi.cah.R
import com.mibaldi.cah.base.activities.BaseMvpActivity
import com.mibaldi.cah.ui.presenters.login.LoginPresenter
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.LoginContract
import com.mibaldi.cah.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class LoginActivity : BaseMvpActivity<LoginContract.View,
        LoginPresenter>(),
        LoginContract.View {
    
    @Inject
    override lateinit var mPresenter : LoginPresenter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var model: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        model = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)
        mPresenter.initialize(model)

        initView()
    }

    private fun initView() {
        btnLogin.onClick { mPresenter.signIn(etUsername.text.toString()) }

        etUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    btnLogin.isEnabled = it.length > 3
                }
            }
        })

        etUsername.setOnEditorActionListener { v, actionId, event ->
            if ((event != null && (event.keyCode == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                mPresenter.signIn(v.text.toString())
            }
            false
        }
    }

}