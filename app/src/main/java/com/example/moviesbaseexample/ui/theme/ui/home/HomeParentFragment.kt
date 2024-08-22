package com.example.moviesbaseexample.ui.theme.ui.home

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.FragmentHomeParentBinding
import com.example.moviesbaseexample.ui.theme.domain.model.Genre
import com.example.moviesbaseexample.ui.theme.ui.base.BaseFragment
import com.example.moviesbaseexample.ui.theme.ui.base.BasePagerAdapter
import com.example.moviesbaseexample.ui.theme.ui.login.LoginViewModel
import com.example.moviesbaseexample.ui.theme.util.extensions.setSafeOnClickListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class HomeParentFragment : BaseFragment(R.layout.fragment_home_parent) {

    private val binding by viewBinding<FragmentHomeParentBinding>()

    private val viewModel by viewModels<HomeParentViewModel>()

    private val viewModelChild by viewModels<HomeViewModel>()

    override fun setCollectEffects() {

        lifecycleScope.launch{

            viewModel.effect.collect{

                when(it){

                    is HomeParentEffect.ChangePage-> Unit
                }
            }
        }
    }

    override fun setCollectStates() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.state.collect{state->

                stateProgressDialog(state.isLoading)

                HomePagerAdapter(childFragmentManager,lifecycle,setFragmentStep(state.genres)).also {adapter->

                    binding.apply{

                        viewPager.adapter=adapter

                        viewPager.isUserInputEnabled = false
                    }

                    TabLayoutMediator(binding.tabLayout, binding.viewPager ) { tab, position ->

                     setTab(tab,position,adapter)

                    }.attach()
                }
            }
        }
    }
    private fun setTab(tab: TabLayout.Tab,position:Int,adapter : HomePagerAdapter){

        tab.text = adapter.fragmenInfotList[position].name

        for (i in 0 until binding.tabLayout.tabCount) {

            (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(i).also {tabView->

                (tabView.layoutParams as ViewGroup.MarginLayoutParams).also {params->

                    params.setMargins(15, 0, 15, 0)

                    tabView.requestLayout()
                }
            }
        }
    }

    override fun setViewListeners() {
    }

    override fun setInitialData() {

        viewModel.getGenres()

        binding.tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab : TabLayout.Tab?) {

                tab?.let {

                    ResourcesCompat.getFont(tab.view.context, R.font.hind_semi_bold)
                        ?.let { setTabTypeface(tab, it) }
                }

            }

            override fun onTabUnselected(tab : TabLayout.Tab?) {
                tab?.let {
                    ResourcesCompat.getFont(tab.view.context, R.font.hind_regular)
                        ?.let { setTabTypeface(tab, it) }
                }
            }

            override fun onTabReselected(tab : TabLayout.Tab?) {

            }

        })
    }

    private fun setFragmentStep(list:List<Genre>):List<FragmentInfo>{

     return  list.map {

            FragmentInfo(
                id=it.id ,
                name=it.name,
                fragment=HomeFragment.newInstance(it.id))
        }
    }

    override fun initText() {

    }
    private fun setTabTypeface(tab: TabLayout.Tab, typeface: Typeface) {
        for (i in 0 until tab.view.childCount) {
            val tabViewChild: View = tab.view.getChildAt(i)
            if (tabViewChild is TextView) tabViewChild.typeface = typeface
        }
    }

}