package com.work.studyaac.paging.presenter

import android.util.Log
import com.work.studyaac.data.repository.PagingRepositoryImpl

class PagingPresenter(
    private val pagingView: PagingContract.View,
    private val pagingRepositoryImpl: PagingRepositoryImpl
) : PagingContract.Presenter {

    override fun getPagingData(page: Int, pageSize: Int, site: String) {
        pagingRepositoryImpl.getPagingData(
            page, pageSize, site,
            onSuccess = {
                Log.d("결과나옴?", it.items.size.toString())
            },
            onFailure = {
                Log.d("결과나옴?", "안나옴")
            }
        )

    }
}