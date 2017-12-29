package com.mibaldi.cah.ui.presenters

import android.content.Context
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.presenters.main.MainPresenter
import com.mibaldi.cah.ui.views.MainContract
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    @Mock lateinit var mockView: MainContract.View
    lateinit var presenter : MainPresenter
    lateinit var router: Router



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = createMockedPresenter()
        router= Router(mock<Context>())
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