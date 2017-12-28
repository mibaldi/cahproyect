package com.mibaldi.cah.ui.views

import com.mibaldi.cah.base.old.BaseMvpPresenter
import com.mibaldi.cah.base.old.BaseMvpView

object MainContract {
    interface View : BaseMvpView {
        fun showCurrentUser(user:String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun init()
    }
}