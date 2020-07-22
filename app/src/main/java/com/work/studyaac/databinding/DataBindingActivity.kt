package com.work.studyaac.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.work.studyaac.R

class DataBindingActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDatabindingBinding

    private var likeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding)

        binding.name = "duksung"
        binding.lastName = "Park"

    }
}