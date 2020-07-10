package com.work.studyaac.data.repository

import com.work.studyaac.data.source.remote.PagingRemoteDataSourceImpl
import com.work.studyaac.network.model.paging.PagingResponse

class PagingRepositoryImpl(
    private val pagingRemoteDataSourceImpl: PagingRemoteDataSourceImpl
) :
    PagingRepository {
    override fun getPagingData(
        page: Int,
        pageSize: Int,
        site: String,
        onSuccess: (response: PagingResponse) -> Unit,
        onFailure: () -> Unit
    ) {
        pagingRemoteDataSourceImpl.getPagingData(page, pageSize, site, onSuccess, onFailure)
    }


    companion object {

        private var instance: PagingRepositoryImpl? = null

        fun getInstance(pagingRemoteDataSourceImpl: PagingRemoteDataSourceImpl): PagingRepositoryImpl =
            instance ?: PagingRepositoryImpl(pagingRemoteDataSourceImpl).also {
                instance = it
            }

    }


}