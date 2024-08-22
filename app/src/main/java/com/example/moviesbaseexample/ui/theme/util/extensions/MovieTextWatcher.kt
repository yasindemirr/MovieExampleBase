package com.example.moviesbaseexample.ui.theme.util.extensions

import android.text.Editable
import android.text.TextWatcher

interface MovieTextWatcher:TextWatcher {
    override fun onTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {

    }
    override fun beforeTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {

    }

    override fun afterTextChanged(p0 : Editable?) {

    }
}