package com.example.brasileirao.features.games.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.example.brasileirao.R
import com.example.brasileirao.base.CellClickListener
import com.example.brasileirao.features.games.adapters.GamesAdapter
import com.example.brasileirao.features.games.viewmodels.GamesViewModel
import com.example.domain.models.Game
import kotlinx.android.synthetic.main.fragment_games.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GamesFragment : Fragment(), CellClickListener<Int> {

    private val gamesViewModel: GamesViewModel by viewModel()

    private val games: MutableList<Game> = ArrayList()
    private val gamesAdapter = GamesAdapter(games, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_games, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(gamesViewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        registerObservables()

        gamesViewModel.start()
    }

    private fun registerObservables() {
        gamesViewModel.gamesLiveData.observe(viewLifecycleOwner) {
            games.clear()
            games.addAll(it)
            gamesAdapter.notifyDataSetChanged()
        }

        swipeRefreshLayout.setOnRefreshListener {
            gamesViewModel.refreshData()
        }

        gamesViewModel.loading.observe(viewLifecycleOwner){
            swipeRefreshLayout.isRefreshing = it
        }
    }

    private fun setupRecyclerView() {
        gamesRecyclerView.apply {
            setHasFixedSize(true)
            adapter = gamesAdapter
        }
    }

    override fun onCellClickListener(data : Int) {
        val action = GamesFragmentDirections.actionGamesFragmentToGameDetailsFragment(data)

        findNavController().navigate(action)
    }

}