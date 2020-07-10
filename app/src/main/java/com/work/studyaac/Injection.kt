package com.work.studyaac

import com.work.studyaac.data.repository.PagingRepositoryImpl
import com.work.studyaac.data.source.remote.PagingRemoteDataSourceImpl
import com.work.studyaac.util.RetrofitInstance

object Injection {

    const val BASE_URL = "https://api.stackexchange.com/2.2/"

    fun providePagingRepository(): PagingRepositoryImpl =
        PagingRepositoryImpl.getInstance(
            PagingRemoteDataSourceImpl.getInstance(
                RetrofitInstance.getInstance(
                    BASE_URL
                )
            )
        )
}