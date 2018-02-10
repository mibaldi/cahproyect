package com.mibaldi.cah.ui.presenters.game.fragments

import com.mibaldi.cah.base.presenters.fragments.BaseFragmentPresenter
import com.mibaldi.cah.data.models.uimodels.Answer
import com.mibaldi.cah.data.models.uimodels.Player
import com.mibaldi.cah.data.models.uimodels.Question
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.managers.TurnFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.viewModels.TurnViewModel
import com.mibaldi.cah.ui.views.GameFragmentContract
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class GameFragmentResponsesPresenter @Inject constructor(val router: Router, val turnManager: TurnFirebaseManager): BaseFragmentPresenter<GameFragmentContract.FragmentResponsesView>(), GameFragmentContract.FragmentResponsesPresenter {

    lateinit var mIdGame : String
    val observerQuestion = android.arch.lifecycle.Observer<Question> { question ->
        question?.let {
            mView?.showQuestion(it)
        }
    }
    val observerPossibles = android.arch.lifecycle.Observer<List<Answer>> { possibles ->
        possibles?.let {
            mView?.showPossibles(it)
        }
    }
    override fun initialize(idGame: String) {
        mIdGame = idGame
        mView?.observePossibles(observerPossibles)
        mView?.observeQuestion(observerQuestion)
    }
}
