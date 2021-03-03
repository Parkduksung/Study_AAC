package com.work.studyaac.lifecycle

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.studyaac.data.model.Person
import com.work.studyaac.data.repository.LifeCycleRepository
import kotlinx.coroutines.launch

class LifeCycleViewModel(private val lifeCycleRepository: LifeCycleRepository) : ViewModel(),
    LifecycleObserver {


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
