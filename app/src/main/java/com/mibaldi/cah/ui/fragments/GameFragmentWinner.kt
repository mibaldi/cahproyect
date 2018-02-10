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
import com.mibaldi.cah.ui.activities.GameActivity
import com.mibaldi.cah.ui.presenters.game.fragments.GameFragmentResponsesPresenter
import com.mibaldi.cah.ui.presenters.game.fragments.GameFragmentWinnerPresenter
import com.mibaldi.cah.ui.viewModels.TurnViewModel
import com.mibaldi.cah.ui.views.GameFragmentContract
import com.mibaldi.cah.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.fragment_game_question.*
import javax.inject.Inject


class GameFragmentWinner : BaseMvpFragment<GameFragmentContract.FragmentWinnerView, GameFragmentWinnerPresenter>(), GameFragmentContract.FragmentWinnerView{


    @Inject
    override lateinit var mPresenter : GameFragmentWinnerPresenter
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var mTurnViewModel: TurnViewModel


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val type = arguments.getString("type")
        val idGame = arguments.getString("idGame")
        mTurnViewModel = ViewModelProviders.of(this,viewModelFactory).get(TurnViewModel::class.java)
        mPresenter.initialize(idGame,type)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_game_winner, container, false)
        return view
    }
    override fun observeWinner(observerWinner: Observer<String>) =
            mTurnViewModel.winner.observe(this,observerWinner)

    override fun showWinner(winner: String) {
        tvWinner.text = "Ganador $winner"
    }
    override fun getMyActivity(): AppCompatActivity {
        return activity as GameActivity
    }

    companion object {
        fun newInstance (idGame: String,type: String): GameFragmentWinner {
            val fragment = GameFragmentWinner()
            val args = Bundle()
            args.putString("type",type)
            args.putString("idGame",idGame)

            fragment.setArguments(args)
            return fragment
        }
    }

}