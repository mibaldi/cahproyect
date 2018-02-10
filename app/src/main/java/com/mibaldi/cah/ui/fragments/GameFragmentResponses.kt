package com.mibaldi.cah.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mibaldi.cah.R
import com.mibaldi.cah.base.fragments.BaseMvpFragment
import com.mibaldi.cah.data.models.uimodels.Answer
import com.mibaldi.cah.data.models.uimodels.Question
import com.mibaldi.cah.ui.activities.GameActivity
import com.mibaldi.cah.ui.presenters.game.fragments.GameFragmentResponsesPresenter
import com.mibaldi.cah.ui.viewModels.TurnViewModel
import com.mibaldi.cah.ui.views.GameFragmentContract
import com.mibaldi.cah.utils.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_game_question.*
import kotlinx.android.synthetic.main.fragment_game_responses.*
import javax.inject.Inject


class GameFragmentResponses: BaseMvpFragment<GameFragmentContract.FragmentResponsesView, GameFragmentResponsesPresenter>(), GameFragmentContract.FragmentResponsesView{


    @Inject
    override lateinit var mPresenter : GameFragmentResponsesPresenter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mTurnViewModel: TurnViewModel


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val idGame = arguments.getString("idGame")
        mTurnViewModel = ViewModelProviders.of(this,viewModelFactory).get(TurnViewModel::class.java)
        mPresenter.initialize(idGame)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_game_responses, container, false)
        return view
    }
    override fun getMyActivity(): AppCompatActivity {
        return activity as GameActivity
    }

    override fun observeQuestion(observerQuestion: Observer<Question>) =
        mTurnViewModel.question.observe(this,observerQuestion)


    override fun observePossibles(observerPossibles: Observer<List<Answer>>) =
            mTurnViewModel.possibles.observe(this,observerPossibles)

    override fun showQuestion(question: Question) {
        tvQuestion.text = question.question
    }

    override fun showPossibles(answers: List<Answer>) {
        tvResponses.text = answers.map { it.textAnswer }.joinToString(",")
    }

    companion object {
        fun newInstance (idGame: String,type: String):GameFragmentResponses{
            val fragment = GameFragmentResponses()
            val args = Bundle()
            args.putString("type",type)
            args.putString("idGame",idGame)

            fragment.setArguments(args)
            return fragment
        }
    }

}