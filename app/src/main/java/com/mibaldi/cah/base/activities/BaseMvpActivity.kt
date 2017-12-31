package com.mibaldi.cah.base.activities
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.mibaldi.cah.base.presenters.activities.BaseMvpPresenter
import com.mibaldi.cah.base.views.BaseMvpView
import com.mibaldi.cah.ui.activities.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
abstract class BaseMvpActivity<in V : BaseMvpView, T : BaseMvpPresenter<V>>
    : DaggerAppCompatActivity(), BaseMvpView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
    }



    override fun getMyActivity(): AppCompatActivity {
        return this
    }
    abstract var mPresenter: T


    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
}

