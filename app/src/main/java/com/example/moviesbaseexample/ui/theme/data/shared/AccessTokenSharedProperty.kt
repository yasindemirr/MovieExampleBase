package com.example.moviesbaseexample.ui.theme.data.shared

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessTokenSharedProperty @Inject constructor(
    enSharedHelpers : SharedPreferences
) : BaseShared<String>(enSharedHelpers) {

    override fun getDefault() = null

    override fun getValue() : String? = getStringValue()

    override fun getKey() = "ACCESS_TOKEN"
}