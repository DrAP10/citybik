package com.base.presentation.home

import androidx.lifecycle.ViewModel
import com.base.domain.Greeting
import com.base.usecases.GetRegretUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(getRegretUseCase: GetRegretUseCase) : ViewModel() {

    private val _greetingState = MutableStateFlow(getRegretUseCase.getRegret("en"))
    val greetingState: StateFlow<Greeting> = _greetingState.asStateFlow()

}