package com.mibaldi.cah.ui.views

import com.mibaldi.cah.base.BaseMvpPresenter
import com.mibaldi.cah.base.BaseMvpView

object MainContract {
    interface View : BaseMvpView {
        fun showCurrentUser(user:String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun init()
    }
}