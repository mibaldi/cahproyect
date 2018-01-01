package com.mibaldi.cah.ui.presenters

import android.content.Context
import com.mibaldi.cah.data.repositories.GameRepository
import com.mibaldi.cah.data.repositories.UserRepository
import com.mibaldi.cah.domain.interactors.configuration.ConfigurationInteractorImpl
import com.mibaldi.cah.domain.interactors.main.MainInteractor
import com.mibaldi.cah.domain.interactors.main.MainInteractorImpl
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.presenters.main.MainPresenter
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.MainContract
import com.mibaldi.cah.utils.ViewModelFactory
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mibaldi.cah.ui.activities.MainActivity
import org.junit.Rule
import org.junit.rules.TestRule



class MainPresenterTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @Mock lateinit var mockView: MainActivity
    lateinit var presenter : MainPresenter
    lateinit var router: Router
    val mainInteractor = MainInteractorImpl(UserRepository(), GameRepository())
    lateinit var model: ViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = createMockedPresenter()
        model = MainViewModel()
        presenter.initializer(model)
    }

    @Test
    fun gotToConfiguration() {
        presenter.goToConfiguration()
    }
    @Test
    fun `should be take the username when presenter call to iteractor`(){
        presenter.getCurrentUser()
    }
    private fun createMockedPresenter(): MainPresenter {
        router= Router(mock<Context>())

        val presenter = MainPresenter(router,mainInteractor)
        presenter.attachView(mockView)
        return presenter
    }

}