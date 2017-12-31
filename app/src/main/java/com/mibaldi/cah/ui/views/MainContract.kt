package com.mibaldi.cah.ui.views

import com.mibaldi.cah.base.presenters.activities.BaseMvpPresenter
import com.mibaldi.cah.base.views.BaseMvpView


object MainContract {
    interface View : BaseMvpView {
        fun showCurrentUser(user:String)
        fun showError(message: String?)
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun initializer()
    }
}