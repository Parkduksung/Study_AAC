package com.work.studyaac.data.source.remote

import android.util.Log
import com.work.studyaac.data.model.Person
import com.work.studyaac.network.room.database.PersonDatabase
import com.work.studyaac.network.room.entity.PersonEntity
import kotlinx.coroutines.*

class LifeCycleLocalDataSourceImpl(private val personDatabase: PersonDatabase) :
    LifeCycleLocalDataSource {
    override fun createPerson(person: Person) {
        CoroutineScope(Dispatchers.IO).launch {
            personDatabase.personDao().registerPerson(
                PersonEntity(
                    personName = person.personName,
                    personAge = person.personAge
                )
            )

            withContext(Dispatchers.Main) {
                Log.d("결과", "여기찍힘")
            }
        }
    }

    override fun getAllList(callback: (list: List<PersonEntity>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val personAllList = personDatabase.personDao().getAllList()

            withContext(Dispatchers.Main) {
                callback(personAllList)
            }
        }

    }
}