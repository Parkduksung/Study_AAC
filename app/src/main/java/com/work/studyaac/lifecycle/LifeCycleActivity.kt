package com.work.studyaac.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.work.studyaac.BR
import com.work.studyaac.R
import com.work.studyaac.databinding.ActivityLifecycleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LifeCycleActivity : AppCompatActivity() {


    private lateinit var lifecycleBinding: ActivityLifecycleBinding

    private val lifeCycleViewModel: LifeCycleViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleBinding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle)
        lifecycleBinding.lifecycleOwner = this
        lifecycleBinding.viewModel = lifeCycleViewModel
        lifecycleBinding.setVariable(BR.viewModel, lifeCycleViewModel)
        setContentView(lifecycleBinding.root)

        lifecycle.addObserver(lifeCycleViewModel)


        lifeCycleViewModel.examTimingLiveData.observe(this, {
            Log.d("결과", it)
        })

    }

    override fun onDestroy() {
        lifecycle.removeObserver(lifeCycleViewModel)
        super.onDestroy()
    }
}