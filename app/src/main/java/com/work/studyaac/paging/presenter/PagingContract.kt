package com.work.studyaac.paging.presenter

interface PagingContract {

    interface View {
        fun showData()
    }

    interface Presenter {
        fun getPagingData(
            page: Int,
            pageSize: Int,
            site: String
        )
    }
}