package com.work.studyaac.lifecycle

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import com.work.studyaac.R
import com.work.studyaac.databinding.ActivityLifecycleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LifeCycleActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "AppCompatActivity"
    }

    private lateinit var lifecycleBinding: ActivityLifecycleBinding

    private val lifeCycleViewModel: LifeCycleViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        Log.d(TAG, "hashCode : ${hashCode()}")


        lifecycleBinding = DataBindingUtil.setContentView(this, R.layout.activity_lifecycle)
        lifecycleBinding.lifecycleOwner = this
        lifecycleBinding.viewModel = lifeCycleViewModel
        setContentView(lifecycleBinding.root)

        lifecycle.addObserver(lifeCycleViewModel)

        //어떤 값의 변화가 생명주기까지 알아야 되면서 해야되는거면 LiveData 가 맞지만 단순 값만의 변화면 ObservableField 사용하는게 좀 더 의미상 맞는듯.
        lifeCycleViewModel.dummyData1.observe(this, { value ->
            Log.d(TAG, "value : $value")
        })

        lifeCycleViewModel.dummyData2.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            }
        })

        lifeCycleViewModel.dialogLiveData.observe(this, { message ->
            if (message != null) {
                AlertDialog.Builder(this)
                    .setMessage(message)
                    .setNeutralButton(
                        "확인"
                    ) { _, _ -> }
                    .create()
                    .show()
            }
        })

        lifeCycleViewModel.personModelLiveData.observe(this, { person ->
            Log.d("결과", "personName : ${person.name}")
            Log.d("결과", "personAge : ${person.age}")
        })


        lifecycleBinding.changeValueButton.setOnClickListener {
//            lifeCycleViewModel.changeDataValue()
//            startActivity(Intent(this, MainActivity::class.java))
//            lifeCycleViewModel.changeComparisionData()
//            lifeCycleViewModel.setPersonLiveData(LifeCycleViewModel.Person.Name("박덕"))
//            lifeCycleViewModel.setDialogMessage("아마또나올꺼야")
            lifeCycleViewModel.createPerson()
        }

        lifeCycleViewModel.personLiveData.observe(this, { type ->
            when (type) {
//                is LifeCycleViewModel.Person.Name -> {
//                    Log.d("결과", "이름는 비밀.")
//                }

                is LifeCycleViewModel.Person.Age -> {
                    Log.d("결과", "나이는 ${type.count}")
                }

                is LifeCycleViewModel.Person.CheckName -> {
                    Log.d("결과", "결과는 ${type.isSame}")
                }

                else -> {
                }
            }
        })


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
        lifecycle.removeObserver(lifeCycleViewModel)
        Log.d(TAG, "onDestroy")
    }
}