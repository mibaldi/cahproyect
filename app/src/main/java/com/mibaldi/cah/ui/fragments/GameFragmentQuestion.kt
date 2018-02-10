package com.mibaldi.cah.ui.fragments

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mibaldi.cah.R
import com.mibaldi.cah.base.fragments.BaseMvpFragment
import com.mibaldi.cah.ui.activities.GameActivity
import com.mibaldi.cah.ui.presenters.game.fragments.GameFragmentQuestionPresenter
import com.mibaldi.cah.ui.views.GameFragmentContract
import kotlinx.android.synthetic.main.fragment_game_question.*
import javax.inject.Inject
import android.widget.Toast
import android.R.interpolator.linear
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.widget.Button
import android.widget.LinearLayout
import com.mibaldi.cah.ui.viewModels.TurnViewModel
import com.mibaldi.cah.utils.ViewModelFactory


class GameFragmentQuestion: BaseMvpFragment<GameFragmentContract.FragmentQuestionView, GameFragmentQuestionPresenter>(),GameFragmentContract.FragmentQuestionView{


    @Inject
    override lateinit var mPresenter : GameFragmentQuestionPresenter
    @Inject
    lateinit var viewModelFactory:ViewModelFactory
    lateinit var mTurnViewModel: TurnViewModel
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
        mTurnViewModel = ViewModelProviders.of(this,viewModelFactory).get(TurnViewModel::class.java)
        mPresenter.initialize(idGame,type)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_game_question, container, false)
        return view
    }
    override fun setType(type: String) {
        tvType.text = "Question type = $type"
    }

    override fun showQuestions(questionList: List<Long>) {
        llQuestions.weightSum = questionList.size.toFloat()
        for (question in questionList){
            generateButton(question)
        }
    }

    private fun generateButton(question: Long){
        val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,1.0f)

        val btn = Button(getMyActivity())
        btn.setId(question.toInt())

        val id_ = btn.getId()
        btn.setText("button  $id_")
        btn.setBackgroundColor(Color.rgb(70, 80, 90))
        llQuestions.addView(btn, params)
        val btn1 = getMyActivity().findViewById(id_) as Button
        btn1.setOnClickListener({ view ->
            mPresenter.setQuestion(id_)
            Toast.makeText(view.context,
                    "Button clicked index = " + id_, Toast.LENGTH_SHORT)
                    .show()
        })
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