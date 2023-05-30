package com.base.presentation.di

import com.base.usecases.di.useCasesModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.base.presentation.home.HomeViewModel
import com.base.presentation.setting.SettingViewModel

fun presentationModule() = module {
    includes(useCasesModule())

    viewModelOf(::HomeViewModel)

    viewModelOf(::SettingViewModel)

}