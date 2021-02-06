package com.work.studyaac.di

import com.work.studyaac.data.source.remote.LifeCycleLocalDataSource
import com.work.studyaac.data.source.remote.LifeCycleLocalDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<LifeCycleLocalDataSource> { LifeCycleLocalDataSourceImpl(get()) }
}