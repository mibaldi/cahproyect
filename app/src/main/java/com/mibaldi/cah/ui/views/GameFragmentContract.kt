package com.mibaldi.cah.ui.views

import com.mibaldi.cah.base.presenters.fragments.BaseMvpFragmentPresenter
import com.mibaldi.cah.base.views.BaseMvpFragmentView

object GameFragmentContract {

    interface FragmentQuestionView : BaseMvpFragmentView {
        fun setType(type:String)
    }
    interface FragmentQuestionPresenter : BaseMvpFragmentPresenter<FragmentQuestionView> {
        fun initialize(idGame: String,type: String)
    }
    interface FragmentResponsesView : BaseMvpFragmentView {
        fun setType(type:String)
    }
    interface FragmentResponsesPresenter : BaseMvpFragmentPresenter<FragmentResponsesView> {
        fun initialize(idGame: String,type: String)
    }

}