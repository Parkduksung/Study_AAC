package com.work.studyaac.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.work.studyaac.App
import com.work.studyaac.paging.adapter.PagingAdapter
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class PagingViewModel : ViewModel() {


    private val executor: Executor = Executors.newFixedThreadPool(10)
    private var newsFeedObservable: LiveData<PagedList<PagingAdapter.FeedItem>>

    init {
        val feedDataFactory = FeedDataSourceFactory()
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        newsFeedObservable = LivePagedListBuilder(feedDataFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()
    }

    fun getNewsFeed(): LiveData<PagedList<PagingAdapter.FeedItem>> {
        return newsFeedObservable
    }

    private class FeedDataSource : PageKeyedDataSource<Int, PagingAdapter.FeedItem>() {
        private val apiService = App.instance.apiService

        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, PagingAdapter.FeedItem>
        ) {
            val items = getNewsFeed("bitcoin", 1)
            callback.onResult(items, null, 2)
        }

        override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, PagingAdapter.FeedItem>
        ) {
            val items = getNewsFeed("bitcoin", params.key)
            callback.onResult(items, params.key + 1)
        }

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, PagingAdapter.FeedItem>
        ) {

        }

        private fun getNewsFeed(name: String, page: Int): List<PagingAdapter.FeedItem> {
            val response = apiService.getNews(name, page).execute()
            var items = emptyList<PagingAdapter.FeedItem>()
            if (response.isSuccessful) {
                val articles = response.body()?.articles ?: return items
                items = articles.asSequence().filter {
                    it.title != null && it.description != null && it.urlToImage != null
                }.map {
                    PagingAdapter.FeedItem(
                        UUID.nameUUIDFromBytes(it.title!!.toByteArray()).toString(),
                        it.title,
                        it.description!!,
                        it.urlToImage!!
                    )
                }.toList()
            }
            return items
        }
    }

    private class FeedDataSourceFactory : DataSource.Factory<Int, PagingAdapter.FeedItem>() {
        override fun create(): DataSource<Int, PagingAdapter.FeedItem> {
            return FeedDataSource()
        }
    }
}