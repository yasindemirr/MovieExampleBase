package com.example.moviesbaseexample.ui.theme.ui.login

import androidx.lifecycle.viewModelScope
import com.example.moviesbaseexample.ui.theme.data.model.CreateSession
import com.example.moviesbaseexample.ui.theme.domain.usecases.Auth.AuthUseCases
import com.example.moviesbaseexample.ui.theme.ui.base.BaseViewModel
import com.example.moviesbaseexample.ui.theme.util.CheckInputState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases : AuthUseCases
) : BaseViewModel<AuthEvent, AuthState, AuthEffect,CheckInputState.LoginCheckInputState>() {

    override fun setInitialState() : AuthState {
        return AuthState()
    }

    override fun handleEvents(event : AuthEvent) {
        when (event) {
            is AuthEvent.ClickLoginButton -> {
                createToken()
            }
        }

    }
    fun checkFields(userName : String,password : String):Boolean{

        var approve=true

        viewModelScope.launch{

            _inputErrorChannel.send( CheckLoginInputFields.checkUserName(userName).also { check ->

                        if (check != CheckInputState.LoginCheckInputState.UserNameCheckInputState.NoErrorInput) {

                            approve = false
                        }

                    })

            _inputErrorChannel.send( CheckLoginInputFields.checkPassword(password).also { check ->

                if (check != CheckInputState.LoginCheckInputState.PasswordCheckInputState.NoErrorInput) {

                    approve = false
                }
            })

        }
        return approve

    }



    private fun createToken() {
        setState { copy(isLoading = true) }

        viewModelScope.launch {

            authUseCases.createTokenUsaCase().onEach { repoResult ->

              repoResult .onSuccess {

                  setState {

                      copy(
                          requestToken = it.requestToken
                      )
                  }
                  applyToken(CreateSession(state.value.userName, state.value.password, it.requestToken))
              }
                  .onResponed {
                      setState {
                          copy(isLoading = false)
                      }
                  }
            }.launchIn(viewModelScope)

        }
    }

    private fun applyToken(model : CreateSession) {

        setState { copy(isLoading = true) }

        viewModelScope.launch {

            authUseCases.getIdUseCase(model).onEach {repoResult ->

               repoResult .onSuccess {

                takeSessionId(it.requestToken)

            }
                .onResponed {

                    setState {

                        copy(isLoading = false)
                    }
                }
            }.launchIn(viewModelScope)

        }
    }

    private fun takeSessionId(requestToken : String?) {

        setState { copy(isLoading = true) }

        viewModelScope.launch {

            authUseCases.takeSessionIdUseCase(requestToken).onEach { repoResult ->

              repoResult.onSuccess {

                setEffect {

                    AuthEffect.SaveToShared(it.sessionId)
                }

                setEffect {

                    AuthEffect.NavigateToHome
                }
            }.onResponed {

                setState {

                    copy(isLoading = false)
                }
            }
            }.launchIn(viewModelScope)
        }
    }
}

sealed interface AuthEvent {
    object ClickLoginButton : AuthEvent
/*    data class ApplyToken(val model : CreateSession) : AuthEvent
    data class IdCreated(val requestToken : String?) : AuthEvent*/
}

sealed interface AuthEffect {
    object NavigateToHome : AuthEffect
    data class SaveToShared(val id : String?) : AuthEffect
}

data class AuthState(
    val isLoading : Boolean = false,
    val requestToken : String? = null,
    var userName : String = "",
    var password : String = "",
    val id : String? = null
)




