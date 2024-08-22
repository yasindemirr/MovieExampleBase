package com.example.moviesbaseexample.ui.theme.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.example.moviesbaseexample.ui.theme.ui.base.BasePagerAdapter

class HomePagerAdapter(
    fragmentManager : FragmentManager,
    lifecycle : Lifecycle,
   val fragmenInfotList:List<FragmentInfo>

):BasePagerAdapter(fragmentManager,lifecycle) {
    override fun getItemCount() : Int {

        return fragmenInfotList.size

    }

    override fun createFragment(position : Int) : Fragment {

        return  fragmenInfotList[position].fragment

    }
}
data class FragmentInfo (
        val id:Int?,
        val fragment : Fragment,
        val name:String?
        )