package com.work.studyaac.data.source.remote

import android.widget.Toast
import com.work.studyaac.App
import com.work.studyaac.data.model.Person

class LifeCycleLocalDataSourceImpl : LifeCycleLocalDataSource {
    override fun createPerson(person: Person) {
        Toast.makeText(App.instance.applicationContext, "${person.name} ${person.age}", Toast.LENGTH_LONG).show()
    }
}