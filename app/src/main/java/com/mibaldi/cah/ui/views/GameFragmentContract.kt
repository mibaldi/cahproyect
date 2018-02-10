package com.mibaldi.cah.ui.views

import android.arch.lifecycle.Observer
import com.mibaldi.cah.base.presenters.fragments.BaseMvpFragmentPresenter
import com.mibaldi.cah.base.views.BaseMvpFragmentView
import com.mibaldi.cah.data.models.uimodels.Answer
import com.mibaldi.cah.data.models.uimodels.Question
import com.mibaldi.cah.ui.viewModels.TurnViewModel

object GameFragmentContract {

    interface FragmentQuestionView : BaseMvpFragmentView {
        fun setType(type:String)
        fun showQuestions(questionList: List<Long>)
    }
    interface FragmentQuestionPresenter : BaseMvpFragmentPresenter<FragmentQuestionView> {
        fun initialize(idGame: String,type: String)
        fun setQuestion(id_: Int)
    }

    interface FragmentResponsesView : BaseMvpFragmentView {
        fun observeQuestion(observerQuestion : Observer<Question>)
        fun observePossibles(observerPossibles : Observer<List<Answer>>)
        fun showQuestion(question: Question)
        fun showPossibles(answers: List<Answer>)
    }
    interface FragmentResponsesPresenter : BaseMvpFragmentPresenter<FragmentResponsesView> {
        fun initialize(idGame: String)
    }

    interface FragmentResultView : BaseMvpFragmentView {
        fun observeQuestion(observerQuestion : Observer<Question>)
        fun observePossibles(observerPossibles : Observer<List<Answer>>)
        fun showQuestion(question: Question)
        fun showPossibles(answers: List<Answer>)

    }
    interface FragmentResultPresenter : BaseMvpFragmentPresenter<FragmentResultView> {
        fun initialize(idGame: String,type: String)
        fun setCorrectResponse(id_: Int)
    }

    interface FragmentWinnerView : BaseMvpFragmentView {
        fun observeWinner(observerWinner : Observer<String>)
        fun showWinner(winner: String)
    }
    interface FragmentWinnerPresenter : BaseMvpFragmentPresenter<FragmentWinnerView> {
        fun initialize(idGame: String,type: String)
    }





}