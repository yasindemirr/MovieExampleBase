package com.example.moviesbaseexample.ui.theme.ui.login

import androidx.lifecycle.viewModelScope
import com.example.moviesbaseexample.ui.theme.data.repository.Auth.AuthRepositoryImpl
import com.example.moviesbaseexample.ui.theme.ui.base.BaseViewModel
import com.example.moviesbaseexample.ui.theme.util.dataresource.RepoResult
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
            is AuthEvent.CreateRequestToken -> createToken()
        }

    }

    private fun createToken() {
        setState { copy(isLoading = isLoading) }
        viewModelScope.launch {
            authRepository.getAuth()
                .onSuccess {
                    setState {
                        copy(
                            isLoading = false,
                            requestToken = it.requestToken
                        )
                    }
                    setEffect {
                        AuthEffect.NavigateToHome
                    }
                    setEffect {
                        AuthEffect.SaveToShared(it.requestToken)
                    }
                }
                .onResponed {
                    setState {
                        copy(isLoading = false)
                    }
                }
        }
    }

}
sealed interface AuthEvent {
    object CreateRequestToken : AuthEvent
}

sealed interface AuthEffect {
    object NavigateToHome : AuthEffect
    data class SaveToShared(val requestToken : String?):AuthEffect
}

data class AuthState(
    val isLoading: Boolean = false,
    val requestToken: String? = null,
    val sessionId: String? = null,
)



