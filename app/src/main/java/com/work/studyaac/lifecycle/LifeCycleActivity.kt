package com.work.studyaac.lifecycle

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.ViewModelProvider
import com.work.studyaac.MainActivity
import com.work.studyaac.R
import com.work.studyaac.databinding.ActivityLifecycleBinding

class LifeCycleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "AppCompatActivity"
    }

    private lateinit var lifecycleBinding: ActivityLifecycleBinding

    private lateinit var lifeCycleViewModel: LifeCycleViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        Log.d(TAG, "hashCode : ${hashCode()}")

        lifecycleBinding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle)
        lifecycleBinding.lifecycleOwner = this
        lifeCycleViewModel = ViewModelProvider(this).get(LifeCycleViewModel::class.java)
        lifecycleBinding.viewModel = lifeCycleViewModel
        setContentView(lifecycleBinding.root)


        //어떤 값의 변화가 생명주기까지 알아야 되면서 해야되는거면 LiveData 가 맞지만 단순 값만의 변화면 ObservableField 사용하는게 좀 더 의미상 맞는듯.
        lifeCycleViewModel.dummyData1.observe(this, { value ->
            Log.d(TAG, "value : $value")
        })


        lifeCycleViewModel.dummyData2.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            }
        })

        lifecycleBinding.changeValueButton.setOnClickListener {
            lifeCycleViewModel.changeDataValue()
            startActivity(Intent(this, MainActivity::class.java))
            lifeCycleViewModel.changeComparisionData()
        }

        //처음엔 둘다 찍히나 아래 B 는 동일한 값이 오면은 로그가 찍히지 않음.
        lifeCycleViewModel.comparisionA.observe(this, { data ->
            Log.d("결과", data)
        })

        lifeCycleViewModel.comparisionB.observe(this, { data ->
            Log.d("결과", data)
        })

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(TAG, "onSaveInstanceState")
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }


    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}