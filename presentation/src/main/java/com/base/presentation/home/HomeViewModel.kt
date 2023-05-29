package com.base.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.domain.Answer
import com.base.domain.Publication
import com.base.usecases.GetPublicationsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(getPublicationsUseCase: GetPublicationsUseCase) : ViewModel() {

    private var _publicationsState: MutableStateFlow<Answer<List<Publication>>> = MutableStateFlow(Answer.Loading)
    val publicationState: StateFlow<Answer<List<Publication>>> = _publicationsState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getPublicationsUseCase.run(46).collect {
                _publicationsState.value = it
            }
        }
    }

}