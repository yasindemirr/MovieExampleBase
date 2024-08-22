package com.example.moviesbaseexample.ui.theme.component

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.MovieToolbarBinding
import com.example.moviesbaseexample.ui.theme.util.extensions.isEnableView

class MovieToolBar@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {

    private val binding : MovieToolbarBinding =MovieToolbarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        attributes(attrs)
    }

    @SuppressLint("Recycle")
    private fun attributes(attrs : AttributeSet?) {
        context.obtainStyledAttributes(attrs, R.styleable.MovieToolBar).also {typeArray->
            with(typeArray){
                getString(R.styleable.MovieToolBar_header)?.let {
                    setTitle(title =it )
                }
                getBoolean(R.styleable.MovieToolBar_leftIconVisibility,true).also {
                    leftIconState(it)
                }


            }


        }
    }

    private fun leftIconState(state : Boolean) {
        binding.backButton.isVisible=state
    }

    fun setTitle(title:String){

        binding.toolbarTitle.text=title
    }
}