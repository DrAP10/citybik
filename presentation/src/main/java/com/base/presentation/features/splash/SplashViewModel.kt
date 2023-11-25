package com.base.presentation.features.splash

import androidx.lifecycle.ViewModel
import com.base.usecases.RequestNetworksUseCase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashViewModel(
    private val requestNetworksUseCase: RequestNetworksUseCase,
) : ViewModel() {

    init {
        requestNetworksList()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun requestNetworksList() {
        GlobalScope.launch(Dispatchers.IO) {
            requestNetworksUseCase().collect {
                /* no-op */
            }
        }
    }

}