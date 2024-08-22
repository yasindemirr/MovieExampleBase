package com.example.moviesbaseexample.ui.theme.util.extensions

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.constraintlayout.helper.widget.MotionPlaceholder
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviesbaseexample.R

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

/**
 * imageview ler src lerini set etmek için kullanılır
 */
fun ImageView.setIcon(@DrawableRes drawableRes: Int) =

    this.setImageDrawable(resDrawable(drawableRes))

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

fun ImageView.loadImage(
    url: String?,
    isCircleCrop: Boolean = false,
    errorImage: Int? = null,
    placeholder : CircularProgressDrawable
) {
    val skipMemoryCache = RequestOptions().skipMemoryCache(false)

    val circleCrop = if (isCircleCrop) RequestOptions().circleCrop() else RequestOptions()

    Glide.with(context)
        .load(url)
        .apply(skipMemoryCache)
        .error(errorImage)
        .apply(circleCrop)
        .into(this)
}
fun placeholderProgressBar(context: Context): CircularProgressDrawable {

    return CircularProgressDrawable(context).apply {

        strokeWidth=8f

        centerRadius=40f

        start()
    }

}



