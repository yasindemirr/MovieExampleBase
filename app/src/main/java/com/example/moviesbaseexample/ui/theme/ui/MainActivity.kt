package com.example.moviesbaseexample.ui.theme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.ActivityMainBinding
import com.example.moviesbaseexample.ui.theme.data.service.interceptor.ErrorEvent
import com.example.moviesbaseexample.ui.theme.data.service.interceptor.HttpErrorHandler
import com.example.moviesbaseexample.ui.theme.ui.base.BaseActivity
import com.example.moviesbaseexample.ui.theme.util.SessionManager
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import org.xml.sax.ErrorHandler
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sessionManager : SessionManager
    private val binding by viewBinding<ActivityMainBinding>()
    private lateinit var errorHandler : HttpErrorHandler
    private  var navController: NavController?=null
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val navGraph = navController!!.navInflater.inflate(R.navigation.nav_graph)

        val startDestination = if (sessionManager.hasSession()) {
            R.id.homeFragment
        } else {
            R.id.loginFragment
        }
        navGraph.setStartDestination(startDestination)
        navController!!.graph = navGraph
        errorHandler=HttpErrorHandler(this)
        ErrorEvent.errorEvent.observe(this){
            errorHandler.handle(it)
        }
    }

}