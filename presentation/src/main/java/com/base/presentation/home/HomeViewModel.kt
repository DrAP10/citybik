package com.base.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.domain.Publication
import com.base.usecases.GetPublicationsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(getPublicationsUseCase: GetPublicationsUseCase) : ViewModel() {

    private var _publicationsState: MutableStateFlow<List<Publication>> = MutableStateFlow(emptyList())
    val publicationState: StateFlow<List<Publication>> = _publicationsState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            _publicationsState.value = getPublicationsUseCase.getPublications(46)
        }
    }

}