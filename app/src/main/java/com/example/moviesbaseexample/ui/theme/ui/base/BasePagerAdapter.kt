package com.example.moviesbaseexample.ui.theme.ui.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

abstract class BasePagerAdapter
    (fragmentManager : FragmentManager,
     lifecycle : Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle){

}