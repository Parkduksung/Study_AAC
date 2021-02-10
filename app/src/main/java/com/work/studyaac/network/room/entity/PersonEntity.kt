package com.work.studyaac.network.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Person")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    val personNum: Int = 0,
    @ColumnInfo(name = "personName")
    val personName: String,
    @ColumnInfo(name = "personAge")
    val personAge: String
)