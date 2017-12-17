package com.mibaldi.cah.base


import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity




abstract class BaseMvpActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>>
    : AppCompatActivity(), BaseMvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }

    override fun getContext(): Context = this

    protected abstract var mPresenter: T

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }




}