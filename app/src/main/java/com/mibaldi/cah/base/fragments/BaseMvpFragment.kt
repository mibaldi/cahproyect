package com.mibaldi.cah.base.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.mibaldi.cah.base.presenters.fragments.BaseMvpFragmentPresenter
import com.mibaldi.cah.base.views.BaseMvpFragmentView
import dagger.android.support.DaggerFragment

@Suppress("UNCHECKED_CAST")
abstract class BaseMvpFragment<in V : BaseMvpFragmentView, T : BaseMvpFragmentPresenter<V>>
    : DaggerFragment(), BaseMvpFragmentView {


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.attachView(this as V )
    }

    override fun getMyFragment(): Fragment {
        return this
    }

    abstract var mPresenter: T
    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}

