package com.mibaldi.cah.ui.views

import com.mibaldi.cah.base.presenters.activities.BaseMvpPresenter
import com.mibaldi.cah.base.views.BaseMvpView
import com.mibaldi.cah.ui.viewModels.MainViewModel

object LoginContract {
    interface View : BaseMvpView {
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun initialize(model: MainViewModel)
        fun signIn(username: String)
    }
}