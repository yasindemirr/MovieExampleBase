package com.example.moviesbaseexample.ui.theme.util.extensions

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun View.makeVisible(){
    this.visibility=View.VISIBLE
}
fun View.makeInvisible(){
    this.visibility=View.INVISIBLE
}
fun View.makeGone(){
    this.visibility=View.GONE
}
fun View.resDrawable(@DrawableRes drawableRes: Int) =
    ContextCompat.getDrawable(context, drawableRes)
fun View.getColor(@ColorRes colorRes : Int) = ContextCompat.getColor(context, colorRes)

fun View.getAsDp(resources : Resources, valueInDp : Float) : Float {

    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, resources.displayMetrics)
}

fun View.getAsSp(resources : Resources, valueInSP : Float) : Float {

    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, valueInSP, resources.displayMetrics)
}

fun ImageView.changeIconColor(@ColorRes iconColorRes:Int)=this.setColorFilter(ContextCompat.getColor(context, iconColorRes))

fun View.isEnableView(enable:Boolean){
    this.apply {
        isEnabled = enable

        alpha = if (enable) 1.0f else 0.4f
    }
}


