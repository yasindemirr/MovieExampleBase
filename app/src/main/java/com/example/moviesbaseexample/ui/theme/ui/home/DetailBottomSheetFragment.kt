package com.example.moviesbaseexample.ui.theme.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.FragmentDetailBottomSheetBinding
import com.example.moviesbaseexample.ui.theme.domain.model.Movie
import com.example.moviesbaseexample.ui.theme.ui.base.BaseBottomSheetFragment
import com.example.moviesbaseexample.ui.theme.ui.home.adapter.MovieAdapter
import com.example.moviesbaseexample.ui.theme.util.extensions.loadImage
import com.example.moviesbaseexample.ui.theme.util.extensions.placeholderProgressBar
import com.hoc081098.viewbindingdelegate.viewBinding

class DetailBottomSheetFragment(val model : Movie) : BaseBottomSheetFragment(R.layout.fragment_detail_bottom_sheet) {

    private val binding by viewBinding<FragmentDetailBottomSheetBinding>()
    override fun setCollectEffects() {
    }

    override fun setCollectStates() {
    }

    override fun setViewListeners() {
    }

    override fun setInitialData() {

        model.apply {

            binding.apply {

                bottomIv.loadImage(posterPath, placeholder =  placeholderProgressBar(requireContext()))

                ratingbar.apply {

                    stepSize=0.5f

                    rating=voteAverage?:3f

                }

                overView.text=overview

                vote.text= voteAverage?.toString()

            }





        }

    }

    override fun initText() {


    }
    companion object {

        fun show(fragmentManager : FragmentManager, model : Movie) {

            DetailBottomSheetFragment(model).also { _dialog ->

                _dialog.arguments = bundleOf()

            }.show(fragmentManager, this::class.simpleName)
        }
    }


}