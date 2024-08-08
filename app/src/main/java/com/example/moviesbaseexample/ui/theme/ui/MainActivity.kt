package com.example.moviesbaseexample.ui.theme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import com.example.moviesbaseexample.R
import com.example.moviesbaseexample.databinding.ActivityMainBinding
import com.example.moviesbaseexample.ui.theme.data.service.interceptor.ErrorEvent
import com.example.moviesbaseexample.ui.theme.data.service.interceptor.HttpErrorHandler
import com.example.moviesbaseexample.ui.theme.datastore.SessionIdDataStoreManager
import com.example.moviesbaseexample.ui.theme.ui.base.BaseActivity
import com.example.moviesbaseexample.ui.theme.util.SessionManager
import com.example.moviesbaseexample.ui.theme.util.extensions.showDialog
import com.hoc081098.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.xml.sax.ErrorHandler
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sessionManager : SessionIdDataStoreManager

    private val binding by viewBinding<ActivityMainBinding>()

    private lateinit var errorHandler : HttpErrorHandler

    private  var navController: NavController?=null
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navControllers()
        showErrorsMessage()


    }
    private fun showErrorsMessage(){
        errorHandler=HttpErrorHandler(this)
        ErrorEvent.errorEvent.observe(this){errorType->
            this.showDialog(
                icon = R.drawable.baseline_warning_24,
                title =getString(R.string.dialog_error_base_title),
                positiveButtonText = getString(R.string.dialog_tek_buton_text),
                description =errorHandler.handle(errorType),
                positiveButtonAction = {
                        dialog ->   dialog.dismiss()
                }
            )
        }
    }
    private fun navControllers(){
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val navGraph = navController!!.navInflater.inflate(R.navigation.nav_graph)
        lifecycleScope.launch {
            println(sessionManager.get())
            val startDestination = if (sessionManager.get().isNullOrEmpty()) {
                R.id.loginFragment
            } else {
                R.id.homeFragment
            }
            navGraph.setStartDestination(startDestination)
            navController!!.graph = navGraph
        }

    }

}