package com.work.studyaac.network.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.work.studyaac.network.room.dao.PersonDao
import com.work.studyaac.network.room.entity.PersonEntity

@Database(entities = [PersonEntity::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}