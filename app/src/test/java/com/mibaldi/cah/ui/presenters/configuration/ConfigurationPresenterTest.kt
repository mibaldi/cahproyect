package com.mibaldi.cah.ui.presenters.configuration

import android.content.Context
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.views.ConfigurationContract
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ConfigurationPresenterTest {

    @Mock lateinit var mockView: ConfigurationContract.View
    lateinit var presenter : ConfigurationPresenter
    lateinit var router: Router



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = createMockedPresenter()
    }

    @Test
    fun `Should be display progress when presenter call changeusername`() {
        presenter.changeUsername("Mikel")
        verify(mockView).showProgress()
        verify(mockView).hideProgress()
    }


    private fun createMockedPresenter(): ConfigurationPresenter {
        router= Router(mock<Context>())

        val presenter = ConfigurationPresenter(router)
        presenter.attachView(mockView)
        return presenter
    }
}