package com.mibaldi.cah.base


interface BaseMvpPresenter<in V : BaseMvpView> {

    fun attachView(view: V)

    fun detachView()
}