package com.work.studyaac.lifecycle

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.work.studyaac.BR
import com.work.studyaac.data.repository.LifeCycleRepository

class LifeCycleViewModel(private val lifeCycleRepository: LifeCycleRepository) : ViewModel(),
    LifecycleObserver {

    private val _dummyData1 = MutableLiveData<String>()
    val dummyData1: LiveData<String> = _dummyData1

    private val _comparisionA = MutableLiveData("same")
    val comparisionA: LiveData<String> = _comparisionA


    private val _comparisionB = MutableLiveData("same")
    val comparisionB: LiveData<String> = _comparisionB.distinctUntilChanged()

    private val _personLiveData = MutableLiveData<Person>()
    val personLiveData: LiveData<Person> = _personLiveData

    private val _dialogLiveData = MutableLiveData<String>()
    val dialogLiveData: LiveData<String> = _dialogLiveData


    val dummyData2 = ObservableField<String>()

    val personInfoLiveData = MutableLiveData<com.work.studyaac.data.model.Person>()


    val personInfo by lazy {
        PersonInfo().apply {
            personName = ""
            personAge = ""
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun initLiveData() {
        _dialogLiveData.value = null
    }

    init {
        _dummyData1.value = "hello"
    }

    fun createPerson() {
        lifeCycleRepository.createPerson(
            personInfoLiveData.value!!
        )
    }

    fun changeDataValue() {
        _dummyData1.value = "change"
    }

    fun setDialogMessage(message: String) {
        _dialogLiveData.value = message
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

class PersonInfo : BaseObservable() {

    @get:Bindable
    var personName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.personName)
        }

    @get:Bindable
    var personAge: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.personAge)
        }

}
