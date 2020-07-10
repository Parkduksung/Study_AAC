package com.work.studyaac.data.source.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.work.studyaac.network.api.PagingApi
import com.work.studyaac.network.model.paging.Item

class PagingDataSourceFactory(private val pagingApi: PagingApi) : DataSource.Factory<Int, Item>() {
    val mutableLiveData = MutableLiveData<PageKeyedDataSource<Int, Item>>()
    private var pagingDataSourceImpl: PagingRemoteDataSourceImpl? = null
    override fun create(): DataSource<Int, Item> {
        pagingDataSourceImpl = PagingRemoteDataSourceImpl(pagingApi)
        mutableLiveData.postValue(pagingDataSourceImpl)
        return pagingDataSourceImpl!!

    }
}