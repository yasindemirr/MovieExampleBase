package com.example.moviesbaseexample.ui.theme.util.helper

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.DialogProgressBinding

object DialogHelper {

    fun setProgressDialog(activity: Activity): Dialog {

        val dialog = Dialog(activity)

        val binding = DialogProgressBinding.inflate(LayoutInflater.from(activity))

        dialog.setContentView(binding.root)

        dialog.setCancelable(false)

        val rotation = AnimationUtils.loadAnimation(activity, R.anim.custom_progress_bar_circle_bg)

        with(binding){

            progressBar.clearAnimation()

            progressBar.startAnimation(rotation)

        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }
}