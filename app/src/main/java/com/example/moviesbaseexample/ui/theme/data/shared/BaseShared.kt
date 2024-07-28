package com.example.moviesbaseexample.ui.theme.data.shared

import android.content.SharedPreferences

abstract class BaseShared<W>(
    private val sharedPref : SharedPreferences
) {
    val editor=sharedPref.edit()

    open fun saveValue(w : W) {

        when (w) {

            is Int -> {

                editor.putInt(getKey(), w as Int)
            }

            is String -> {

                editor.putString(getKey(), w as String)
            }

            is Boolean -> {

                editor.putBoolean(getKey(), w as Boolean)
            }

            is Long -> {

                editor.putLong(getKey(), w as Long)
            }
        }
    }

    protected fun getIntegerValue() : Int {

        return sharedPref.getInt(getKey(), getDefault() as Int)
    }

    protected fun getStringValue() : String? {

        return sharedPref.getString(getKey(), getDefault() as? String)
    }

    protected fun getBooleanValue() : Boolean {

        return sharedPref.getBoolean(getKey(), getDefault() as Boolean)
    }

    protected fun getLongValue() : Long {

        return sharedPref.getLong(getKey(), getDefault() as Long)
    }

    fun delete() {
        editor.remove(getKey()).apply()
    }

    abstract fun getDefault() : W?

    abstract fun getValue() : W?

    protected abstract fun getKey() : String
}