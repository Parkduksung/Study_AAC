package com.work.studyaac.data.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.work.studyaac.BR

class Person : BaseObservable() {
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