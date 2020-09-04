package com.example.brasileirao.features.game_details.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brasileirao.R
import com.example.domain.models.Game
import com.example.domain.models.LiveGame
import kotlinx.android.synthetic.main.item_games.view.*

class LiveGameAdapter(private val liveGames: List<LiveGame>) :
    RecyclerView.Adapter<LiveGameAdapter.BaseLiveGameViewHolder<LiveGame>>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseLiveGameViewHolder<LiveGame> {
        return when (viewType) {
            TYPE_TEXT -> TextViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_game_live_simple_text, parent, false))
            TYPE_PLAYER -> PlayerViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_game_live_simple_text, parent, false))
            TYPE_TWITTER -> TwitterViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_game_live_simple_text, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount() = liveGames.size

    override fun onBindViewHolder(holder: BaseLiveGameViewHolder<LiveGame>, position: Int) {
        val gameModel = liveGames[position]

        when(holder){
            is TextViewHolder -> holder.bind(gameModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(liveGames[position]){
            is LiveGame -> TYPE_TEXT
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    class TextViewHolder(itemView: View) : BaseLiveGameViewHolder<LiveGame>(itemView) {
        override fun bind(item: LiveGame) {

        }
    }

    class PlayerViewHolder(itemView: View) : BaseLiveGameViewHolder<LiveGame>(itemView) {
        override fun bind(item: LiveGame) {

        }
    }

    class TwitterViewHolder(itemView: View) : BaseLiveGameViewHolder<LiveGame>(itemView) {
        override fun bind(item: LiveGame) {

        }
    }

    abstract class BaseLiveGameViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }

    companion object {
        private const val TYPE_TEXT = 0
        private const val TYPE_PLAYER = 1
        private const val TYPE_TWITTER = 2
    }
}