package com.mibaldi.cah.ui.fragments

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mibaldi.cah.R
import com.mibaldi.cah.base.fragments.BaseMvpFragment
import com.mibaldi.cah.ui.activities.GameActivity
import com.mibaldi.cah.ui.presenters.game.fragments.question.GameFragmentQuestionPresenter
import com.mibaldi.cah.ui.views.GameFragmentContract
import kotlinx.android.synthetic.main.fragment_game_question.*
import javax.inject.Inject

class GameFragmentQuestion: BaseMvpFragment<GameFragmentContract.FragmentQuestionView, GameFragmentQuestionPresenter>(),GameFragmentContract.FragmentQuestionView{

    @Inject
    override lateinit var mPresenter : GameFragmentQuestionPresenter

    override fun getMyActivity(): AppCompatActivity {
        return activity as GameActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type = arguments.getString("type")
        val idGame = arguments.getString("idGame")
        mPresenter.initialize(idGame,type)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_game_question, container, false)
        return view
    }
    override fun setType(type: String) {
        tvType.text = "Question type = $type"
    }

    companion object {
        fun newInstance (idGame: String,type: String):GameFragmentQuestion{
            val fragment = GameFragmentQuestion()
            val args = Bundle()
            args.putString("type",type)
            args.putString("idGame",idGame)

            fragment.setArguments(args)
            return fragment
        }
    }

}