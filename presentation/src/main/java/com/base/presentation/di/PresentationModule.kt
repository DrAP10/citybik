package com.base.presentation.di

import com.base.usecases.di.useCasesModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.base.presentation.features.home.HomeViewModel
import com.base.presentation.features.setting.SettingViewModel

fun presentationModule() = module {
    includes(useCasesModule())

    viewModelOf(::HomeViewModel)

    viewModelOf(::SettingViewModel)

}