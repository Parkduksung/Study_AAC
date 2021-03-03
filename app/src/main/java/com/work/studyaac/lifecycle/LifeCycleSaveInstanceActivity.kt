package com.work.studyaac.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.work.studyaac.R
import com.work.studyaac.databinding.ActivityLifecycleSaveInstanceBinding

class LifeCycleSaveInstanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLifecycleSaveInstanceBinding

    private lateinit var viewModel: LifeCycleSaveInstanceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle_save_instance)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LifeCycleSaveInstanceViewModel::class.java)

    }
}