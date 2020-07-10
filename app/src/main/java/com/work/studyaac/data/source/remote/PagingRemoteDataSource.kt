package com.work.studyaac.data.source.remote

import com.work.studyaac.network.model.paging.PagingResponse

interface PagingRemoteDataSource {

    fun getPagingData(
        page: Int,
        pageSize: Int,
        site: String,
        onSuccess: (response: PagingResponse) -> Unit,
        onFailure: () -> Unit
    )

}