@file:Suppress("IllegalIdentifier")
package com.mibaldi.cah.ui.presenters.configuration

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.mibaldi.cah.data.repositories.UserRepository
import com.mibaldi.cah.domain.interactors.configuration.ConfigurationInteractor
import com.mibaldi.cah.domain.interactors.configuration.ConfigurationInteractorImpl
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.activities.ConfigurationActivity
import com.mibaldi.cah.ui.activities.MainActivity
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.ConfigurationContract
import com.mibaldi.cah.utils.ViewModelFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ConfigurationPresenterTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @Mock lateinit var mockView: ConfigurationActivity
    lateinit var presenter : ConfigurationPresenter
    lateinit var router: Router
    val viewModelFactory = ViewModelFactory()
    val configurationInteractor = ConfigurationInteractorImpl(UserRepository())
    lateinit var model: ViewModel



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = createMockedPresenter()
        model = MainViewModel()
        presenter.initializer(model)
    }

    @Test
    fun `Should be display progress when presenter call changeusername`() {
        presenter.changeUsername("Mikel")
        verify(mockView).showProgress()
        verify(mockView).hideProgress()
    }


    private fun createMockedPresenter(): ConfigurationPresenter {
        router= Router(mock<Context>())
        val presenter = ConfigurationPresenter(router,configurationInteractor)
        presenter.attachView(mockView)
        return presenter
    }
}