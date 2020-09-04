package com.example.brasileirao.features.game_details.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.brasileirao.R
import com.example.brasileirao.features.game_details.adapters.LiveGameAdapter
import com.example.brasileirao.features.game_details.viewmodels.GameDetailsViewModel
import com.example.domain.models.LiveGame
import kotlinx.android.synthetic.main.bottomsheet_live_game.*
import kotlinx.android.synthetic.main.partial_game_details_score.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameDetailsFragment : Fragment() {

    private val gameDetailsViewModel: GameDetailsViewModel by viewModel()

    private val games: MutableList<LiveGame> = ArrayList()
    private val gamesAdapter = LiveGameAdapter(games)

    val args: GameDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        registerObservables()

        gameDetailsViewModel.start(args.gameId)
    }

    private fun registerObservables() {
        gameDetailsViewModel.gameLiveData.observe(viewLifecycleOwner) { game ->
            roundText.text = getString(R.string.game_details_round, game.round)

            game.homeTeam?.let { homeTeam ->
                stadiumText.text = homeTeam.stadium
                homeTeamNameText.text = homeTeam.name
                context?.let {
                    Glide
                        .with(it)
                        .load(Uri.parse(homeTeam.logo))
                        .into(homeTeamLogo)
                }
            }

            game.guestTeam?.let { guestTeam ->
                guestTeamNameText.text = guestTeam.name
                context?.let {
                    Glide
                        .with(it)
                        .load(Uri.parse(guestTeam.logo))
                        .into(guestTeamLogo)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        liveGameRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                this@GameDetailsFragment.context,
                LinearLayoutManager.VERTICAL,
                true
            )
            adapter = gamesAdapter
        }
    }
}