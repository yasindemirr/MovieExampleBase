package com.example.moviesbaseexample.ui.theme.component

import android.annotation.SuppressLint
import android.content.Context
import android.text.InputFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.BaseEdittextBinding
import com.example.moviesbaseexample.ui.theme.util.extensions.afterTextChanged
import com.example.moviesbaseexample.ui.theme.util.extensions.makeGone
import com.example.moviesbaseexample.ui.theme.util.extensions.makeVisible
import com.example.moviesbaseexample.ui.theme.util.extensions.pureText
import com.example.moviesbaseexample.ui.theme.util.extensions.resDrawable
import com.example.moviesbaseexample.ui.theme.util.extensions.setIcon
import com.example.moviesbaseexample.ui.theme.util.extensions.setPasswordInput
import com.example.moviesbaseexample.ui.theme.util.extensions.setSafeOnClickListener
import com.example.moviesbaseexample.ui.theme.util.extensions.setTextInput

class MovieEditText @JvmOverloads constructor(
    context : Context, attrs : AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding : BaseEdittextBinding = BaseEdittextBinding.inflate(

        LayoutInflater.from(context), this, true
    )
    private var inputLimit:Int=Default.limitValue

    private var filter:InputFilter?=null

    private var inputTextType : EdittextType=Default.defaultTextType

    private var errorTextIsDisable : Boolean=false

    private var afterChangeInputListener:((String)->Unit)?=null

    private var passWordInvisible:Boolean=false
    private object Default {
        val defaultTypeId=EdittextType.Text.id
        const val limitValue=50
        const val disableErrorText=false
        val defaultTextType=EdittextType.Text
    }
    init {
        attributes(attrs)
        setViewListener()
        setInputType()
    }

    @SuppressLint("Recycle")
    private fun attributes(attrs : AttributeSet?) {

        context.obtainStyledAttributes(attrs,R.styleable.MovieEditText).also {typedArray ->

            inputTextType= EdittextType.getById(typedArray.getInt(R.styleable.MovieEditText_editType,Default.defaultTypeId)).also {

                setEditTextType(it)

                setInputType(it)

            }

            inputLimit=typedArray.getInt(R.styleable.MovieEditText_inputLimit,Default.limitValue)

            typedArray.getString(R.styleable.MovieEditText_title)?.let {title ->

                setTitle(title)

            }

            typedArray.getString(R.styleable.MovieEditText_hint)?.let {hint ->

                setHint(hint)

            }

            typedArray.getBoolean(R.styleable.MovieEditText_disableErrorMessage,Default.disableErrorText).let{

                errorTextIsDisable=it
            }

        }
    }

    private fun setHint(hint : String) {

        binding.editText.hint = hint
    }

    private fun setViewListener() {

        binding.apply {

            editText.afterTextChanged {

                it?.let { editable ->

                    setInputLimitText(editable.toString().length)

                    if (editable.isNotEmpty()) {

                        hideErrorMessage()
                    }

                    afterChangeInputListener?.invoke(editable.toString())
                }

                setInputType()

            }
            rightIcon.setSafeOnClickListener {

                when (inputTextType) {

                    EdittextType.Text,
                    EdittextType.DecimalNumber,
                    EdittextType.Search -> {

                      setText("")
                    }

                    EdittextType.Password -> {

                        passWordInvisible=passWordInvisible.not()

                        if (passWordInvisible) {

                            editText.setTextInput()

                            rightIcon.setIcon(R.drawable.ic_open_eyes_monkey)

                        } else {

                            editText.setPasswordInput()

                            rightIcon.setIcon(R.drawable.ic_close_eyes_monkey)
                        }
                        editText.setSelection(getText().length)
                    }
                }
            }
        }
    }

    fun setInputType(inputType : EdittextType) {

        with(binding){
            when (inputType) {

                EdittextType.Password -> {

                    //input editText alanının inputType ını password olarak değiştirir
                    editText.setPasswordInput()
                }


                EdittextType.DecimalNumber -> {

                    editText.inputType = android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL or android.text.InputType.TYPE_CLASS_NUMBER
                }

                EdittextType.Text,EdittextType.Search -> {

                    editText.inputType = android.text.InputType.TYPE_CLASS_TEXT
                }

            }
        }


    }

    fun afterChangeInput(listener:(String)->Unit){

        afterChangeInputListener=listener
    }

    private fun setInputType(){

        with(binding){

            rightIcon.makeVisible()

            when(inputTextType){

                EdittextType.Text,
                EdittextType.DecimalNumber,
                EdittextType.Search->{

                    rightIcon.setIcon(R.drawable.baseline_close_24)

                    if (editText.pureText().isNotEmpty()) {

                        rightIcon.makeVisible()

                    }else {

                        rightIcon.makeGone()

                    }
                }

                EdittextType.Password->{

                    if (passWordInvisible){

                        rightIcon.setIcon(R.drawable.ic_open_eyes_monkey)

                    }else {

                        rightIcon.setIcon(R.drawable.ic_close_eyes_monkey)
                    }
                }

            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setInputLimitText(currentLimit : Int) {

        binding.apply {
            if (inputLimit != Default.limitValue) {

                showInputLimit.text = "$currentLimit/$inputLimit"

                showInputLimit.makeVisible()

                if (inputLimit == currentLimit) showInputLimit.setTextColor(resources.getColorStateList(R.color.edit_text_succes_green, null))

                else showInputLimit.setTextColor(resources.getColorStateList(R.color.edit_text_error_red, null))

            } else showInputLimit.makeGone()

            binding.editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(inputLimit))
        }
    }

    /* private fun setFilter(state:Boolean){

         filter = InputFilter { source, start, end, dest, dstart, dend ->
             val regex = Regex("[a-zA-Z]")

             // Girdiyi kontrol et
             val filtered = source.filter { regex.matches(it.toString()) }

             // Harf dışında bir karakter girilmişse, sadece harfleri döndür
             if (filtered.length != source.length) {
                 filtered
             } else {
                null
             }
         }
         if (state) filter!! else filter=null

         binding.editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(inputLimit),filter!!)
     }*/

    fun getText():String{

        return  binding.editText.text.toString()
    }

    private fun setEditTextType(edittextType : EdittextType) {

        binding.editText.background = resDrawable(edittextType.background)


    }

    fun setTitle(title:String){

        binding.titleTv.apply {

            text=title

            makeVisible()

        }
    }
    /**
     *  [setErrorMessageText] fonksiyonu tetiklenmedikçe ya da [R.styleable.MovieEditText_disableErrorMessage] set edilmedikçe errorText gösterilmez
     */
    fun setErrorText(errorMessage:String) {

        if (errorTextIsDisable.not() && errorMessage.isNotEmpty()) {

            setErrorMessageText(errorMessage)

            showErrorMessage()

        } else {

            hideErrorMessage()

        }
        this.invalidate()
    }
    fun setText(input : String?) {

        if (input.isNullOrBlank()) {

            binding.editText.setText("")

        } else {

            binding.editText.setText(input)
        }
    }

    private fun hideErrorMessage()=binding.errorTv.makeGone()

    private fun showErrorMessage()=binding.errorTv.makeVisible()

    private fun setErrorMessageText(errorMessageText : String) {

        binding.errorTv.text = errorMessageText

    }
}
enum class EdittextType(
    val id:Int,
    val background:Int=(R.drawable.radius_10_purple_button_border_bg),

    ){
    Text(1),

    Search(2,R.drawable.radius_10_white_edittext_filled_bg),

    Password(3),

    DecimalNumber(4);
    companion object {
        fun getById(id:Int):EdittextType{

            return values().find {

                it.id==id
            }!!
        }
    }
}
