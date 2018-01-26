package com.mibaldi.cah.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mibaldi.cah.R
import com.mibaldi.cah.data.models.Game
import kotlinx.android.synthetic.main.item_games_list.view.*

class GameListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var games : List<Game> = arrayListOf()
    lateinit var listener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClickListener(game: Game)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_games_list, parent, false)
        return GameHolder(view, listener)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val viewHolder = holder as GameHolder
        viewHolder.bindItem(games[position])
    }
    override fun getItemCount(): Int = games.size

    fun setDataAndListener(list: List<Game>, callback: OnItemClickListener){
        games = mutableListOf()
        games = list
        listener = callback
        notifyDataSetChanged()
    }

    class GameHolder(itemView: View, callback: OnItemClickListener): RecyclerView.ViewHolder(itemView) {
        lateinit var game: Game
        var listner : OnItemClickListener = callback

        fun bindItem(item: Game){
            game = item
            itemView.tvName.text = item.name
            itemView.tvNumberPlayers.text = item.numPlayers.toString()
            itemView.setOnClickListener { listner.onItemClickListener(game)}
        }
    }




}