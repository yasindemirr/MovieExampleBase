package com.example.moviesbaseexample.ui.theme.ui.login

import androidx.lifecycle.viewModelScope
import com.example.moviesbaseexample.ui.theme.data.model.CreateSession
import com.example.moviesbaseexample.ui.theme.data.repository.Auth.AuthRepositoryImpl
import com.example.moviesbaseexample.ui.theme.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepositoryImpl
):BaseViewModel<AuthEvent,AuthState,AuthEffect>() {


    override fun setInitialState() : AuthState {
        return AuthState()
    }

    override fun handleEvents(event : AuthEvent) {
        when (event) {
            is AuthEvent.ClickLoginButton ->{
                createToken()
            }
            is AuthEvent.ApplyToken->  applyToken(event.model)

            is AuthEvent.IdCreated-> takeSessionId(event.requestToken)
        }

    }

    private fun createToken() {
        setState { copy(isLoading = isLoading) }
        viewModelScope.launch {
            authRepository.getAuth()
                .onSuccess {
                    setState {
                        copy(
                            requestToken = it.requestToken
                        )
                    }
                    setEvent(AuthEvent.ApplyToken(CreateSession(state.value.userName, state.value.password, it.requestToken)))
                }
                .onResponed {
                    setState {
                        copy(isLoading = false)
                    }
                }
        }
    }
    private fun applyToken(model:CreateSession) {
        setState { copy(isLoading = isLoading) }
        viewModelScope.launch {
            authRepository.getId(model)
                .onSuccess {
                   setEvent(AuthEvent.IdCreated(it.requestToken))
                }
                .onResponed {
                    setState {
                        copy(isLoading = false)
                    }
                }
        }
    }
    private fun takeSessionId(requestToken:String?) {
        setState { copy(isLoading = true) }
        viewModelScope.launch {
            authRepository.getSessionId(requestToken)
                .onSuccess {
                    setEffect {
                        AuthEffect.SaveToShared(it.sessionId)
                    }
                    setEffect {
                        AuthEffect.NavigateToHome
                    }
                }
                .onResponed {
                    setState {
                        copy(isLoading = false)
                    }
                }
        }
    }
    fun checkUserNameAndPassword():Boolean{
        val currentSate=state.value
       return currentSate.password.isNotEmpty() && currentSate.userName.isNotEmpty()
    }

}
sealed interface AuthEvent {
    object ClickLoginButton : AuthEvent
    data class ApplyToken(val model : CreateSession) : AuthEvent
    data class IdCreated(val requestToken: String?) : AuthEvent
}

sealed interface AuthEffect {
    object NavigateToHome : AuthEffect
    data class SaveToShared(val id : String?):AuthEffect
}

data class AuthState(
    val isLoading: Boolean = false,
    val requestToken: String? = null,
    var userName:String="",
    var password:String="",
    val id : String?=null
)




