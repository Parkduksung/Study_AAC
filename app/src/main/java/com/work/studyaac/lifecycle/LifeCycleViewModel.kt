package com.work.studyaac.lifecycle

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.distinctUntilChanged

class LifeCycleViewModel : ViewModel() {

    private val _dummyData1 = MutableLiveData<String>()
    val dummyData1: LiveData<String> = _dummyData1


    private val _comparisionA = MutableLiveData("same")
    val comparisionA: LiveData<String> = _comparisionA


    private val _comparisionB = MutableLiveData("same")
    val comparisionB: LiveData<String> = _comparisionB.distinctUntilChanged()


    private val _personLiveData = MutableLiveData<Person>()
    val personLiveData: LiveData<Person> = _personLiveData


    val dummyData2 = ObservableField<String>()

    init {
        _dummyData1.value = "hello"
    }


    fun changeDataValue() {
        _dummyData1.value = "change"
    }


    fun setPersonLiveData(person: Person) {
        when (person) {
            is Person.CurrentAge -> {
                Log.d("결과", "여기탐")
                _personLiveData.value = Person.Age(30)
            }

            is Person.Name -> {
                _personLiveData.value = Person.CheckName(person.name == "박덕성")
            }

            else -> {
            }
        }
        _personLiveData.value = person
    }


    fun changeComparisionData() {
        _comparisionA.value = "same"
        _comparisionB.value = "same"
    }


    sealed class Person {
        object CurrentAge : Person()
        data class CheckName(val isSame: Boolean) : Person()
        data class Name(val name: String) : Person()
        data class Age(val count: Int) : Person()
    }
}