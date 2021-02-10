package com.work.studyaac.data.source.remote

import android.util.Log
import com.work.studyaac.data.model.Person
import com.work.studyaac.network.room.database.PersonDatabase
import com.work.studyaac.network.room.entity.PersonEntity

class LifeCycleLocalDataSourceImpl(private val personDatabase: PersonDatabase) :
    LifeCycleLocalDataSource {
    override fun createPerson(person: Person) {
        Thread {
            personDatabase.personDao().registerPerson(
                PersonEntity(
                    personName = person.personName,
                    personAge = person.personAge
                )
            )
        }.start()
        Log.d("결과", "여기찍힘")
    }
}