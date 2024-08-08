package com.example.moviesbaseexample.ui.theme.ui.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.FragmentLoginBinding
import com.example.moviesbaseexample.ui.theme.data.model.CreateSession
import com.example.moviesbaseexample.ui.theme.datastore.SessionIdDataStoreManager
import com.example.moviesbaseexample.ui.theme.ui.base.BaseFragment
import com.example.moviesbaseexample.ui.theme.util.SessionManager
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding by viewBinding<FragmentLoginBinding>()
    private val viewModel by viewModels<LoginViewModel>()

    @Inject
    lateinit var sessionManager : SessionIdDataStoreManager

    override fun setCollectEffects() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.effect.collect{
                when(it){
                    is AuthEffect.NavigateToHome,-> findNavController().navigate(R.id.homeFragment)
                    is  AuthEffect.SaveToShared->  sessionManager.save(it.id)
                }

            }
        }
    }

    override fun setCollectStates() {

    }

    override fun setViewListeners() {
        binding.loginButtonButton.setOnClickListener {
          binding.apply {
              viewModel.setState {
                  copy(userName=loginUserNameEt.text.toString(), password = loginPasswordEt.text.toString())
              }
          }
                if (viewModel.checkUserNameAndPassword()) {
                    viewModel.setEvent(AuthEvent.ClickLoginButton)
                } else {
                    Toast.makeText(context, "Please enter both username and password", Toast.LENGTH_SHORT).show()
                }
            }

        }

    override fun setInitialData() {
        binding.loginButtonButton.buttonIsEnable(false)
    }

    override fun initText() {

    }

}