package com.work.studyaac.di

import com.work.studyaac.lifecycle.LifeCycleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LifeCycleViewModel(get()) }
}