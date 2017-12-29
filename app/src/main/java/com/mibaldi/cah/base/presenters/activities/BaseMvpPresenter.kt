package com.mibaldi.cah.base.presenters.activities

import com.mibaldi.cah.base.views.BaseMvpView

interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view: V)

    fun detachView()
}