package com.mibaldi.cah.ui.presenters.game.fragments

import android.util.Log
import com.mibaldi.cah.base.presenters.fragments.BaseFragmentPresenter
import com.mibaldi.cah.data.models.Player
import com.mibaldi.cah.managers.GameFirebaseManager
import com.mibaldi.cah.managers.TurnFirebaseManager
import com.mibaldi.cah.router.Router
import com.mibaldi.cah.ui.viewModels.MainViewModel
import com.mibaldi.cah.ui.views.GameFragmentContract
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class GameFragmentQuestionPresenter @Inject constructor(val router: Router, val turnManager: TurnFirebaseManager): BaseFragmentPresenter<GameFragmentContract.FragmentQuestionView>(), GameFragmentContract.FragmentQuestionPresenter {


    lateinit var mIdGame : String
    lateinit var mModel: MainViewModel
    lateinit var questionList: List<Long>
    override fun initialize(idGame: String, type: String) {
        initGame()
        mIdGame = idGame
        if (Player.Type.Narrator.name == type){
            mView?.setType(type)
            turnManager.getQuestions(idGame,object :Observer<List<Long>>{
                override fun onComplete() {
                    mView?.showQuestions(questionList)
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(qList: List<Long>) {
                  questionList  = qList
                }

                override fun onError(e: Throwable) {
                }

            })

        }else {

        }

    }
    override fun setQuestion(id_: Int) {
        turnManager.setQuestion(mIdGame,id_.toLong(),object : Observer<Boolean>{
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(t: Boolean) {
                Log.d("setQuestion","pregunta $id_")
            }

            override fun onError(e: Throwable) {
                Log.e("setQuestion",e.message)
            }

        })
    }


    fun initGame(){
    }
}
