package com.example.moviesbaseexample.ui.theme.component

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.BaseButtonBinding
import com.example.moviesbaseexample.ui.theme.util.extensions.changeIconColor
import com.example.moviesbaseexample.ui.theme.util.extensions.getAsSp
import com.example.moviesbaseexample.ui.theme.util.extensions.isEnableView
import com.example.moviesbaseexample.ui.theme.util.extensions.makeVisible
import com.example.moviesbaseexample.ui.theme.util.extensions.resDrawable

class MovieButton @JvmOverloads constructor(
    context : Context, attrs : AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding : BaseButtonBinding = BaseButtonBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private object Default {
        val buttonType = ButtonTpe.FILLED
        const val buttonTextSize = 25F
    }

    init {
        attributes(attrs)
    }

    @SuppressLint("Recycle", "CustomViewStyleable")
    private fun attributes(attrs : AttributeSet?) {

        context.obtainStyledAttributes(attrs, R.styleable.MovieButton).also { typedArray ->
            ButtonTpe.getById(typedArray.getInt(R.styleable.MovieButton_buttonType, Default.buttonType.attrId))?.let {
                setButtonType(it)
            }
            typedArray.getString(R.styleable.MovieButton_buttonText)?.let {

                setText(it)
            } ?: "BASE"

            typedArray.getDimensionPixelSize(R.styleable.MovieButton_buttonTextSize, getAsSp(resources, Default.buttonTextSize).toInt()).toFloat().let { textSize ->

                setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
            }

            typedArray.getDrawable(R.styleable.MovieButton_buttonIcon)?.let {

                setIcon(it)
            }

        }

    }

    fun setIcon(drawable : Drawable) {

        binding.apply {

            imageViewButtonIcon.makeVisible()

            imageViewButtonIcon.setImageDrawable(drawable)

        }

    }


    private fun setButtonType(buttonTpe : ButtonTpe) {

        background = resDrawable(buttonTpe.background)

        setTextColor(resources.getColorStateList(buttonTpe.textColor, null))

        binding.imageViewButtonIcon.changeIconColor(buttonTpe.textColor)


    }

    fun setTextColor(s : ColorStateList) = binding.textViewButtonText.setTextColor(s)

    fun setText(text : String) {

        binding.textViewButtonText.text = text
    }

    fun setTextSize(unit : Int, size : Float) {

        binding.textViewButtonText.setTextSize(unit, size)
    }

    fun buttonIsEnable(enable : Boolean) {

       this.isEnableView(enable)

    }

    override fun setEnabled(enabled : Boolean) {
        super.setEnabled(enabled)
    }

}
enum class ButtonTpe(
    val attrId:Int,
    val background:Int,
    val textColor:Int
){
    FILLED(1, R.drawable.radius_10_purple_button_bg, R.color.white),
    BORDER(2, R.drawable.radius_10_purple_button_border_bg, R.color.movie_purple);
    companion object{
        fun getById(id:Int):ButtonTpe?{
            return  values().find{ it.attrId==id }
        }
    }
}