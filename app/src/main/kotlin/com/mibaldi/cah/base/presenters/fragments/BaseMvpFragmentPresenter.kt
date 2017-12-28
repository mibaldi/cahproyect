package com.mibaldi.cah.base.presenters.fragments

import com.mibaldi.cah.base.views.BaseMvpFragmentView

interface BaseMvpFragmentPresenter<in V : BaseMvpFragmentView> {

    fun attachView(view: V)

    fun detachView()
}