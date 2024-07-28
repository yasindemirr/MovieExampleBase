package com.example.moviesbaseexample.ui.theme.ui.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.FragmentLoginBinding
import com.example.moviesbaseexample.ui.theme.ui.base.BaseFragment
import com.example.moviesbaseexample.ui.theme.util.SessionManager
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding by viewBinding<FragmentLoginBinding>()
    private val viewModel by viewModels<LoginViewModel>()

    @Inject
    lateinit var sessionManager : SessionManager

    override fun setCollectEffects() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.effect.collect{
                when(it){
                    is AuthEffect.NavigateToHome,-> findNavController().navigate(R.id.homeFragment)
                    is  AuthEffect.SaveToShared->  sessionManager.updateAccessToken(it.requestToken)
                }

            }
        }
    }

    override fun setViewListeners() {
        binding.loginButtonButton.setOnClickListener {
            viewModel.setEvent(AuthEvent.CreateRequestToken)
        }
    }

    override fun setInitialData() {

    }

}