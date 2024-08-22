package com.example.moviesbaseexample.ui.theme.ui.base

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import com.example.moviesbaseexample.ui.theme.util.fragmentInterface.FragmentIF
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment(@LayoutRes contentLayoutId : Int): BottomSheetDialogFragment(contentLayoutId),FragmentIF {

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInitialData()

        initText()

        setCollectEffects()

        setViewListeners()

        setCollectStates()
    }

    override fun onStart() {
        super.onStart()

        val displayRectangle = Rect()

        val window: Window = requireActivity().window

        window.decorView.getWindowVisibleDisplayFrame(displayRectangle)

        val (max,min)=(displayRectangle.height() * 0.98f).toInt() to (displayRectangle.height() * 0.98f).toInt()

        dialog?.also {

            val bottomSheet = it.findViewById<View>(

                com.google.android.material.R.id.design_bottom_sheet
            )
            bottomSheet?.layoutParams?.height = max

            val behavior = BottomSheetBehavior.from<View>(bottomSheet!!)

            behavior.peekHeight = min

            view?.requestLayout()
        }
    }
}