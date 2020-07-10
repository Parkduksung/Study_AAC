package com.work.studyaac.data.repository

import com.work.studyaac.network.model.paging.PagingResponse

interface PagingRepository {
    fun getPagingData(
        page: Int,
        pageSize: Int,
        site: String,
        onSuccess: (response: PagingResponse) -> Unit,
        onFailure: () -> Unit
    )
}