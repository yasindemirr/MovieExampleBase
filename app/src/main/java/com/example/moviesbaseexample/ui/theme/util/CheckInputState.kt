package com.example.moviesbaseexample.ui.theme.util

import androidx.compose.ui.input.key.Key.Companion.W
import com.example.moviesbaseexample.ui.theme.util.constants.Constants

sealed interface CheckInputState {

    sealed class LoginCheckInputState(  val condition : Int):CheckInputState {
        sealed class UserNameCheckInputState(

            condition : Int

        ):LoginCheckInputState(condition) {
            object Short : UserNameCheckInputState(Constants.kullaniciAdiMinLenght)
            object Empty : UserNameCheckInputState(Constants.NONEED)
            object NoErrorInput : UserNameCheckInputState(Constants.NONEED)
        }
        sealed class PasswordCheckInputState(

             condition : Int

        ) :LoginCheckInputState(condition) {
            object Short : PasswordCheckInputState(Constants.passwordMinLenght)
            object Empty : PasswordCheckInputState(Constants.NONEED)
            object NoErrorInput : PasswordCheckInputState(Constants.NONEED)
        }
    }
}