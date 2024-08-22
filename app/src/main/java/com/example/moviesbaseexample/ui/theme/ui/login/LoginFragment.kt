package com.example.moviesbaseexample.ui.theme.ui.login

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.FragmentLoginBinding
import com.example.moviesbaseexample.ui.theme.datastore.SessionIdDataStoreManager
import com.example.moviesbaseexample.ui.theme.ui.base.BaseFragment
import com.example.moviesbaseexample.ui.theme.util.CheckInputState
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNREACHABLE_CODE")
@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val binding by viewBinding<FragmentLoginBinding>()

    private val viewModel by viewModels<LoginViewModel>()

    override fun setCollectEffects() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.effect.collect {

                when (it) {

                    is AuthEffect.NavigateToHome -> findNavController().navigate(R.id.homeParentFragment)

                    is AuthEffect.SaveToShared -> sessionManager.save(it.id)

                    else -> {

                    }
                }
            }
        }
            lifecycleScope.launch {

                    viewModel.inputErrorChannel.collect { checkInputState ->

                        with(binding) {

                            when (checkInputState) {

                                CheckInputState.LoginCheckInputState.UserNameCheckInputState.Empty ->

                                    loginUserNameEt.setErrorText("Kullanıcı adı boş olamaz")

                                CheckInputState.LoginCheckInputState.UserNameCheckInputState.Short ->

                                    loginUserNameEt.setErrorText(String.format("Kullanıcı adı %1d karakterden kısa olamaz", checkInputState.condition))

                                CheckInputState.LoginCheckInputState.PasswordCheckInputState.Empty ->

                                    loginPasswordEt.setErrorText("Password boş olamaz")

                                CheckInputState.LoginCheckInputState.PasswordCheckInputState.Short ->

                                    loginPasswordEt.setErrorText(String.format("Password %1d karakterden kısa olamaz", checkInputState.condition))

                                else -> {}
                            }
                        }
                }
        }
    }

    override fun setCollectStates() {

        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.state.collect { state ->

                  stateProgressDialog(state.isLoading)
                }
            }
        }
    }


    override fun setViewListeners() {

        binding.loginButtonButton.setOnClickListener {

            with(binding) {

                val state = viewModel.checkFields(loginUserNameEt.getText(), loginPasswordEt.getText())

                viewModel.setState {

                    copy(userName = loginUserNameEt.getText(), password = loginPasswordEt.getText())
                }

                if (state) {

                    viewModel.setEvent(AuthEvent.ClickLoginButton)

                }
            }
        }
    }

    override fun setInitialData() {

        binding.loginButtonButton.buttonIsEnable(true)
    }

    override fun initText() {

    }


}

