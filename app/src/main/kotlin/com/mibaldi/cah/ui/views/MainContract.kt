package com.mibaldi.cah.ui.views

import com.mibaldi.cah.base.presenters.actitivities.BaseMvpPresenter
import com.mibaldi.cah.base.views.BaseMvpView


object MainContract {
    interface View : BaseMvpView {
        fun showCurrentUser(user:String)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun initializer()
    }
}