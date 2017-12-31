package com.mibaldi.cah.ui.presenters

import android.content.Context
import com.mibaldi.cah.data.repositories.UserRepository
import com.mibaldi.cah.domain.interactors.main.MainInteractor
import com.mibaldi.cah.domain.interactors.main.MainInteractorImpl
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
    lateinit var interactor: MainInteractor



    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = createMockedPresenter()
    }

    @Test
    fun gotToConfiguration() {
        presenter.goToConfiguration()
    }

    private fun createMockedPresenter(): MainPresenter {
        router= Router(mock<Context>())
        interactor = MainInteractorImpl(UserRepository())
        val presenter = MainPresenter(router,interactor)
        presenter.attachView(mockView)
        return presenter
    }

}