package com.work.studyaac.lifecycle

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.*
import com.work.studyaac.data.model.Person
import com.work.studyaac.data.repository.LifeCycleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LifeCycleViewModel(private val lifeCycleRepository: LifeCycleRepository) : ViewModel(),
    LifecycleObserver {

    private val _examTimingLiveData = MutableLiveData<String>()
    val examTimingLiveData: LiveData<String> = _examTimingLiveData.distinctUntilChanged()


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun timingCheckExam() {
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                _examTimingLiveData.postValue("postSetValue1")
                _examTimingLiveData.postValue("postSetValue2")
                _examTimingLiveData.value = "setValue1"
                _examTimingLiveData.postValue("postSetValue3")
                _examTimingLiveData.value = "setValue2"
            }
        }

    }


    fun renewPerson() {
        if (personInfo.personName.isNotEmpty() && personInfo.personAge.isNotEmpty()) {
            lifeCycleRepository.createPerson(personInfo)
        }
    }


    fun getAllList() {
        lifeCycleRepository.getAllList { getAllPersonEntity ->
            val getAllList = getAllPersonEntity.map { it.toPerson() }
            getAllList.forEach {
                Log.d("결과", "name : ${it.personName} , age : ${it.personAge}")
            }
        }
    }

    val personInfo by lazy {
        Person().apply {
            personName = ""
            personAge = ""
        }
    }
}
