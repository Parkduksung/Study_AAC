package com.work.studyaac.di

import com.work.studyaac.data.repository.LifeCycleRepository
import com.work.studyaac.data.repository.LifeCycleRepositoryImpl
import org.koin.dsl.module


val repositoryModule = module {
    single<LifeCycleRepository> { LifeCycleRepositoryImpl(get()) }
}