package com.example.moviesbaseexample.ui.theme.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.FragmentHomeBinding
import com.example.moviesbaseexample.ui.theme.domain.model.Movie
import com.example.moviesbaseexample.ui.theme.ui.base.BaseFragment
import com.example.moviesbaseexample.ui.theme.ui.home.adapter.MovieAdapter
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()

    private val viewModel by viewModels<HomeViewModel>()

    private val movieAdapter by lazy { MovieAdapter(:: movieItemClick) }


    companion object {

        private const val GENRE_ID = "genre_id"

        fun newInstance(genreId : Int?) : HomeFragment {

            return HomeFragment().apply {

                arguments = Bundle().apply {

                    putInt(GENRE_ID, genreId!!)
                }
            }
        }
    }



    override fun setCollectEffects() {

        lifecycleScope.launch {

            viewModel.effect.collect{

                when(it){

                    is HomeEffect.OpenBottomSheet-> DetailBottomSheetFragment.show(childFragmentManager,it.model)
                }
            }
        }
    }

    override fun setCollectStates() {

        viewLifecycleOwner.lifecycleScope.launch{

            viewModel.state.collectLatest{state->

                stateProgressDialog(state.isLoading)

                movieAdapter.submitData(state.filteredMovies)
            }
        }

    }

    override fun setViewListeners() {


    }
    private fun movieItemClick(value: Movie){

        viewModel.setEvent(HomeEvent.MovieClicked(value))
    }
    private fun setAdapter(){

        binding.movieRec.adapter=movieAdapter
    }
    override fun setInitialData() {

        setAdapter()

        arguments?.getInt(GENRE_ID).also {

            viewModel.filteredList(it)
        }
    }

    override fun initText() {
    }

}