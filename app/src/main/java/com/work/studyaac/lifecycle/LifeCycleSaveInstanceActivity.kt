package com.work.studyaac.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.work.studyaac.R
import com.work.studyaac.databinding.ActivityLifecycleSaveInstanceBinding

class LifeCycleSaveInstanceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLifecycleSaveInstanceBinding

    private val viewModel: LifeCycleSaveInstanceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("결과", "LifeCycleSaveInstanceActivity onCreate")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle_save_instance)
        binding.lifecycleOwner = this
        setContentView(binding.root)


        viewModel.countLiveData.observe(this, Observer {
            binding.text.text = it.toString()
        })

        binding.button.setOnClickListener {
            viewModel.incCounter()
        }
    }


    override fun onResume() {
        Log.d("결과", "LifeCycleSaveInstanceActivity onResume")
        super.onResume()
    }


    override fun onPause() {
        Log.d("결과", "LifeCycleSaveInstanceActivity onPause")
        super.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("결과", "LifeCycleSaveInstanceActivity onSaveInstanceState")
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        Log.d("결과", "LifeCycleSaveInstanceActivity onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("결과", "LifeCycleSaveInstanceActivity onDestroy")
        super.onDestroy()
    }
}