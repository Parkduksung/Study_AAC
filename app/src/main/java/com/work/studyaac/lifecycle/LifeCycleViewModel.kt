package com.work.studyaac.lifecycle

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.work.studyaac.data.model.Person
import com.work.studyaac.data.repository.LifeCycleRepository

class LifeCycleViewModel(private val lifeCycleRepository: LifeCycleRepository) : ViewModel(),
    LifecycleObserver {


    fun renewPerson() {
        if (personInfo.personName.isNotEmpty() && personInfo.personAge.isNotEmpty()) {
            lifeCycleRepository.createPerson(personInfo)
        }
    }


    val personInfo by lazy {
        Person().apply {
            personName = ""
            personAge = ""
        }
    }
}
