package com.mibaldi.cah.ui.presenters

import android.content.Context
import com.mibaldi.cah.application.App
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.views.MainContract
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock lateinit var mockView: MainContract.View
    lateinit var presenter : MainPresenter
    @InjectMocks lateinit var router: Router
    @InjectMocks lateinit var app: App



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = createMockedPresenter()
        router.mContext = app
        presenter.router = router

    }

    @Test
    fun gotToConfiguration() {
        presenter.goToConfiguration()
    }

    private fun createMockedPresenter(): MainPresenter {
        val presenter = MainPresenter()
        presenter.attachView(mockView)
        return presenter
    }

}