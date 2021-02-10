package com.work.studyaac.di

import androidx.room.Room
import com.work.studyaac.network.room.database.PersonDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            PersonDatabase::class.java,
            "person"
        ).fallbackToDestructiveMigration()
            .build()
    }
}