package com.example.moviesbaseexample.ui.theme.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.ActivitySplashBinding
import com.example.moviesbaseexample.ui.theme.ui.base.BaseActivity
import com.hoc081098.viewbindingdelegate.viewBinding


@SuppressLint("CustomSplashScreen")
class SplashActivity() :BaseActivity() {
    private val binding by viewBinding<ActivitySplashBinding>()
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }
}