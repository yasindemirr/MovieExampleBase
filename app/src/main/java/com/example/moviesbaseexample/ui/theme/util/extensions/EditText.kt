package com.example.moviesbaseexample.ui.theme.util.extensions

import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.widget.EditText

fun EditText.afterTextChanged(action:(Editable?)->Unit){

   addTextChangedListener(object :MovieTextWatcher{

      override fun afterTextChanged(p0 : Editable?) {

         super.afterTextChanged(p0)

         action(p0)

      }

   })
}
fun EditText.onTextChanged(action:(CharSequence?)->Unit){

   addTextChangedListener(object :MovieTextWatcher{

      override fun onTextChanged(p0 : CharSequence?, p1 : Int, p2 : Int, p3 : Int) {
         super.onTextChanged(p0, p1, p2, p3)

         action(p0)
      }

   })
}
fun EditText.setPasswordInput() {

   //font değişimi olmasın diye ilk önce typeFace objesini alıyoruz, iş bitince tekrar set ediyoruz
   val oldTypeFace = typeface

   inputType = android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD or android.text.InputType.TYPE_CLASS_TEXT

   transformationMethod = PasswordTransformationMethod.getInstance()

   typeface = oldTypeFace
}

/**
 * editText in standart text alacak şekilde gösterilmesi
 */
fun EditText.setTextInput() {

   //font değişimi olmasın diye ilk önce typeFace objesini alıyoruz, iş bitince tekrar set ediyoruz
   val oldTypeFace = typeface

   inputType = android.text.InputType.TYPE_CLASS_TEXT

   transformationMethod = null

   typeface = oldTypeFace
}
fun EditText.pureText():String{

  return this.text.toString().trim()
}
