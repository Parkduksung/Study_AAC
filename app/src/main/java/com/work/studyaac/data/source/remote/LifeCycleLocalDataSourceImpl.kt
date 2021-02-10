package com.work.studyaac.data.source.remote

import android.util.Log
import com.work.studyaac.data.model.Person
import com.work.studyaac.network.room.database.PersonDatabase
import com.work.studyaac.network.room.entity.PersonEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
}