package com.mibaldi.cah.base.presenters.fragments

import com.mibaldi.cah.base.views.BaseMvpFragmentView


open class BaseFragmentPresenter<V : BaseMvpFragmentView> : BaseMvpFragmentPresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }


    override fun detachView() {
        mView = null
    }
}