package com.base.presentation.features.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.domain.Answer
import com.base.domain.Network
import com.base.usecases.GetNetworkDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NetworkDetailViewModel(
    private val getNetworkDetailUseCase: GetNetworkDetailUseCase,
) : ViewModel() {

    private var _networkDetailState: MutableStateFlow<Answer<Network>> =
        MutableStateFlow(Answer.Loading)
    val networkState: StateFlow<Answer<Network>> = _networkDetailState.asStateFlow()

    fun getNetworkDetail(networkId: String) {
        viewModelScope.launch(Dispatchers.Main) {
            getNetworkDetailUseCase(networkId).collect {
                _networkDetailState.value = it
            }
        }
    }

}