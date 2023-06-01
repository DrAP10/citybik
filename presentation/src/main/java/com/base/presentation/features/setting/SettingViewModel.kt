package com.base.presentation.features.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.base.domain.Answer
import com.base.domain.Publication
import com.base.usecases.GetLocalPublicationsUseCase
import com.base.usecases.ResetLocalPublicationsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingViewModel(
    private val getLocalPublicationsUseCase: GetLocalPublicationsUseCase,
    private val resetLocalPublicationsUseCase: ResetLocalPublicationsUseCase
) : ViewModel() {

    private var _publicationsState: MutableStateFlow<Answer<List<Publication>>> =
        MutableStateFlow(Answer.Loading)
    val publicationState: StateFlow<Answer<List<Publication>>> = _publicationsState.asStateFlow()

    init {
        getPublications()
    }

    private fun getPublications() {
        viewModelScope.launch(Dispatchers.Main) {
            getLocalPublicationsUseCase().collect {
                _publicationsState.value = it
            }
        }
    }

    fun resetPublications() =
        viewModelScope.launch(Dispatchers.Main) {
            resetLocalPublicationsUseCase().collect {
                getPublications()
            }
        }

}