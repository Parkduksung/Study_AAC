package com.work.studyaac.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.work.studyaac.R
import com.work.studyaac.databinding.ActivityLifecycleBinding

class LifeCycleActivity : AppCompatActivity() {

    private lateinit var lifecycleBinding: ActivityLifecycleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleBinding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle)
        lifecycleBinding.lifecycleOwner = this
        setContentView(lifecycleBinding.root)

    }
}