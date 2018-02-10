package com.mibaldi.cah.ui.presenters.game.fragments

import com.mibaldi.cah.base.presenters.fragments.BaseFragmentPresenter
import com.mibaldi.cah.data.models.uimodels.Answer
import com.mibaldi.cah.data.models.uimodels.Question
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.GameFragmentContract
import javax.inject.Inject


class GameFragmentResultPresenter @Inject constructor(val router: Router, val gameManager: GameFirebaseManager): BaseFragmentPresenter<GameFragmentContract.FragmentResultView>(), GameFragmentContract.FragmentResultPresenter {



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
    override fun initialize(idGame: String, type: String) {
        mIdGame = idGame
        mView?.observePossibles(observerPossibles)
        mView?.observeQuestion(observerQuestion)
    }
    override fun setCorrectResponse(id_: Int) {

    }


}
