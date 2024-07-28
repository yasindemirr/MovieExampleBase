package com.example.moviesbaseexample.ui.theme.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.moviesbaseexample.ui.theme.util.flow.UIFlow

abstract class BaseFragment(contentLayoutId : Int):Fragment(contentLayoutId),UIFlow {

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()
        setCollectEffects()
        setViewListeners()
    }



}