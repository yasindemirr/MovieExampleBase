package com.example.moviesbaseexample.ui.theme.util.flow

interface UIFlow {

    /**
     * [Flow] [Channel] gibi objelerin dinleneceği metot
     *
     */
    fun setCollectEffects()
    fun setCollectStates()

    /**
     * view lara ait listener olacaksa örneğin [View.OnClickListener] bu metot içinde verilmeli
     *
     */
    fun setViewListeners()

    /**
     * uygulama içi statik değerlerin set edilmesi, o yüzden viewmodel e gerek yok
     */
    fun setInitialData()
    fun initText()
}