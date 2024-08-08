package com.example.moviesbaseexample.ui.theme.util.extensions

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.DialogViewBinding

@SuppressLint("ResourceType")
fun Context.showDialog(
    title:String?=null,
    // icon zorunlu opsiyonel değil
    icon:Int,
    description : String?=null,
    positiveButtonText:String?=null,
    negativeButtonText:String?=null,
    positiveButtonAction:((Dialog)->Unit)?=null,
    negativeButtonAction:((Dialog)->Unit)?=null
):Dialog{
    val dialog=Dialog(this, R.style.Theme_Dialog)
    val binding=DialogViewBinding.inflate(dialog.layoutInflater)
    dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setCancelable(false)
    dialog.setContentView(binding.root)
    binding.apply {

        if (title.isNullOrEmpty().not()){
            dialogTitle.text=title
            dialogTitle.makeVisible()
        }
        if (description.isNullOrEmpty().not()){
            dialogDescription.text=description
            dialogDescription.makeVisible()
        }
        if (positiveButtonText.isNullOrEmpty().not()){
            dialogFirstButton.text=positiveButtonText
            dialogFirstButton.makeVisible()
        }else  dialogFirstButton.makeGone()
        if (negativeButtonText.isNullOrEmpty().not()){
            dialogSecondButton.text=negativeButtonText
            dialogSecondButton.makeVisible()
        }else  dialogSecondButton.makeGone()
        dialogFirstButton.setSafeOnClickListener {
            positiveButtonAction?.invoke(dialog)
        }
        dialogSecondButton.setSafeOnClickListener {
            negativeButtonAction?.invoke(dialog)
        }
        Glide.with(this@showDialog).load(icon).into(dialogIcon)
        iconCloseBtn.setSafeOnClickListener {
            dialog.dismiss()
        }
    }


    dialog.window?.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT
    )
    dialog.window?.setGravity(Gravity.CENTER)
    dialog.show()
    return dialog
}
