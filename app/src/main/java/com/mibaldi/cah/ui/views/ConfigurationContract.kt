package com.mibaldi.cah.ui.views

import com.mibaldi.cah.base.presenters.activities.BaseMvpPresenter
import com.mibaldi.cah.base.views.BaseMvpView


object ConfigurationContract {
    interface View : BaseMvpView {
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun initializer()
    }
}