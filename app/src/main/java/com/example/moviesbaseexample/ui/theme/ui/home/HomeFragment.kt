package com.example.moviesbaseexample.ui.theme.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.FragmentHomeBinding
import com.example.moviesbaseexample.ui.theme.datastore.SessionIdDataStoreManager
import com.example.moviesbaseexample.ui.theme.ui.base.BaseFragment
import com.example.moviesbaseexample.ui.theme.util.extensions.setSafeOnClickListener
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val binding by viewBinding<FragmentHomeBinding>()

    @Inject
    lateinit var sessionManager:SessionIdDataStoreManager
    override fun setCollectEffects() {

    }

    override fun setCollectStates() {

    }

    override fun setViewListeners() {
        binding.deleteSessionId.setSafeOnClickListener {
            lifecycleScope.launch {
                sessionManager.delete()
                findNavController().navigate(R.id.loginFragment)
            }
        }

    }

    override fun setInitialData() {

    }

    override fun initText() {
    }

}