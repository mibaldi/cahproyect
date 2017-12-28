package com.mibaldi.cah.base.presenters.actitivities

import com.mibaldi.cah.base.views.BaseMvpView

open class BasePresenter<V : BaseMvpView> : BaseMvpPresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }


    override fun detachView() {
        mView = null
    }
}