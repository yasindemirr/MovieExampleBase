package com.example.moviesbaseexample.ui.theme.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.moviesbaseexample.ui.theme.datastore.SessionIdDataStoreManager
import com.example.moviesbaseexample.ui.theme.util.fragmentInterface.FragmentIF
import com.example.moviesbaseexample.ui.theme.util.helper.DialogHelper
import javax.inject.Inject

abstract class BaseFragment(contentLayoutId : Int):Fragment(contentLayoutId),FragmentIF {

    private val progressDialog: Dialog by lazy { DialogHelper.setProgressDialog(requireActivity()) }

    @Inject
    lateinit var sessionManager: SessionIdDataStoreManager

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()
        initText()
        setCollectEffects()
        setViewListeners()
        setCollectStates()
    }

    fun stateProgressDialog(state:Boolean){
        if (state) showProgressDialog() else hideProgressDialog()
    }
    fun showProgressDialog(){

        if(progressDialog.isShowing.not()) progressDialog.show()

    }
    fun hideProgressDialog(){

        if(progressDialog.isShowing) progressDialog.dismiss()

    }




}