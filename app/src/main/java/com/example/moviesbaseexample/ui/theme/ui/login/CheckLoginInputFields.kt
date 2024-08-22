package com.example.moviesbaseexample.ui.theme.ui.login

import com.example.moviesbaseexample.ui.theme.util.CheckInputState
import com.example.moviesbaseexample.ui.theme.util.constants.Constants

object CheckLoginInputFields {

    fun checkUserName(userName : String) : CheckInputState.LoginCheckInputState.UserNameCheckInputState{

        return if (userName.isEmpty()) {

            CheckInputState.LoginCheckInputState.UserNameCheckInputState.Empty

        } else if (CheckInputState.LoginCheckInputState.UserNameCheckInputState.Short.condition > userName.length) {

            CheckInputState.LoginCheckInputState.UserNameCheckInputState.Short

        } else {

            CheckInputState.LoginCheckInputState.UserNameCheckInputState.NoErrorInput
        }
    }

    fun checkPassword(password : String) : CheckInputState.LoginCheckInputState.PasswordCheckInputState {

        return if (password.isEmpty()) {

            CheckInputState.LoginCheckInputState.PasswordCheckInputState.Empty

        } else if (CheckInputState.LoginCheckInputState.PasswordCheckInputState.Short.condition > password.length) {

            CheckInputState.LoginCheckInputState.PasswordCheckInputState.Short

        } else {

            CheckInputState.LoginCheckInputState.PasswordCheckInputState.NoErrorInput
        }
    }
}