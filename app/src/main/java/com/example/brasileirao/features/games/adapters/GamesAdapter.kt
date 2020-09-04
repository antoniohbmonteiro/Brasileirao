package com.example.brasileirao.features.games.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brasileirao.R
import com.example.brasileirao.base.CellClickListener
import com.example.domain.models.Game
import kotlinx.android.synthetic.main.item_games.view.*

class GamesAdapter(
    private val games: List<Game>,
    private val cellClickListener: CellClickListener<Int>
) :
    RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_games, parent, false),
            cellClickListener
        )
    }

    override fun getItemCount() = games.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val gameModel = games[position]

        holder.bind(gameModel)
    }

    class ViewHolder(
        itemView: View,
        private val listener: CellClickListener<Int>
    ) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(gameModel: Game) {
            gameModel.homeTeam?.let {
                itemView.homeAcronymTxt.text = it.acronym
                itemView.gameLocationTxt.text = it.stadium
                Glide
                    .with(itemView.context)
                    .load(Uri.parse(it.logo))
                    .into(itemView.homeTeamLogo)
            }
            gameModel.guestTeam?.let {
                itemView.guestAcronymTxt.text = it.acronym
                Glide
                    .with(itemView.context)
                    .load(it.logo)
                    .into(itemView.guestTeamLogo)
            }

            itemView.gameDateTimeTxt.text =
                itemView.context.resources.getString(
                    R.string.item_games_date_time,
                    gameModel.date,
                    gameModel.time
                )

            itemView.setOnClickListener { listener.onCellClickListener(gameModel.id) }
        }

    }
}