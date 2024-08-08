package com.example.moviesbaseexample.ui.theme.util.extensions

import android.os.SystemClock
import android.view.View

class SafeClickListenerExt(
    private var defaultInterval: Int = 500,
    private val onSafeCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSafeCLick(v)
    }
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListenerExt {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}

