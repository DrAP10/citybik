package com.base.presentation.features.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.domain.Answer
import com.base.domain.Network
import com.base.usecases.GetNetworksLocalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NetworksListViewModel(
    private val getNetworksLocalUseCase: GetNetworksLocalUseCase,
) : ViewModel() {

    private var _networkState: MutableStateFlow<Answer<List<Network>>> = MutableStateFlow(Answer.Loading)
    val networksState: StateFlow<Answer<List<Network>>> = _networkState.asStateFlow()

    init {
        getNetworksList()
    }

    fun getNetworksList(term: String = "") {
        viewModelScope.launch(Dispatchers.Main) {
            getNetworksLocalUseCase(term).collect {
                _networkState.value = it
            }
        }
    }
}