package com.mibaldi.cah.ui.views

import com.mibaldi.cah.base.presenters.fragments.BaseMvpFragmentPresenter
import com.mibaldi.cah.base.views.BaseMvpFragmentView

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
        fun setType(type:String)
    }
    interface FragmentResponsesPresenter : BaseMvpFragmentPresenter<FragmentResponsesView> {
        fun initialize(idGame: String,type: String)
    }

    interface FragmentResultView : BaseMvpFragmentView {
        fun setType(type:String)
    }
    interface FragmentResultPresenter : BaseMvpFragmentPresenter<FragmentResultView> {
        fun initialize(idGame: String,type: String)
    }

    interface FragmentWinnerView : BaseMvpFragmentView {
        fun setType(type:String)
    }
    interface FragmentWinnerPresenter : BaseMvpFragmentPresenter<FragmentWinnerView> {
        fun initialize(idGame: String,type: String)
    }



}