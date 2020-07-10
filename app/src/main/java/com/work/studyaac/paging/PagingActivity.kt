package com.work.studyaac.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.work.studyaac.Injection
import com.work.studyaac.R
import com.work.studyaac.paging.adapter.PagingAdapter
import com.work.studyaac.paging.presenter.PagingContract
import com.work.studyaac.paging.presenter.PagingPresenter
import com.work.studyaac.viewmodel.PagingViewModel
import kotlinx.android.synthetic.main.paging_main.*


//페이징이 뭐냐?
// 내 앱의 리사이클러뷰에 점진적이고 우아하게 데이터를 로드하는 것을 쉽게 해주는 라이브러리이다.

//페이징 라이브러리를 사용하면 작은 데이터 청크를 한 번에 로드하여 표시할 수 있다.
//요청에 따라 일부 데이터를 로드하면 네트워크 대역폭 및 시스템 리소스 사용량을 줄일 수 있습니다.

class PagingActivity : AppCompatActivity(), PagingContract.View {

    private lateinit var presenter: PagingContract.Presenter

    private val viewModel by lazy { ViewModelProvider(this).get(PagingViewModel::class.java) }

    private val pagingAdapter by lazy { PagingAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paging_main)

        presenter = PagingPresenter(this, Injection.providePagingRepository())

        rv_paging.run {
            this.adapter = pagingAdapter
        }

        viewModel.getPaging().observe(this@PagingActivity, Observer(pagingAdapter::submitList))
    }

    override fun showData() {

    }
}