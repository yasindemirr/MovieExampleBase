package com.example.moviesbaseexample.ui.theme.data.model.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ErrorResponse(
    val statusMessage: String?=null
) : Parcelable