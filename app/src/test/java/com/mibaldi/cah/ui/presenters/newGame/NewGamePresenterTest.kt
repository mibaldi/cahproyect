package com.mibaldi.cah.ui.presenters.newGame

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.mibaldi.cah.data.repositories.UserRepository
import com.mibaldi.cah.domain.interactors.configuration.ConfigurationInteractorImpl
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.activities.ConfigurationActivity
import com.mibaldi.cah.ui.activities.NewGameActivity
import com.mibaldi.cah.ui.presenters.configuration.ConfigurationPresenter
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.utils.ViewModelFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class NewGamePresenterTest {

    @Mock lateinit var mockView: NewGameActivity
    lateinit var presenter : NewGamePresenter
    lateinit var router: Router
    lateinit var gameManager : GameFirebaseManager


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        gameManager = GameFirebaseManager()
        presenter = createMockedPresenter()
    }

    @Test
    fun `Should create new game in Firebase Database`() {
        presenter.createGame()
    }


    private fun createMockedPresenter(): NewGamePresenter {
        router= Router(mock<Context>())
        val presenter = NewGamePresenter(router, gameManager)
        presenter.attachView(mockView)
        return presenter
    }
}