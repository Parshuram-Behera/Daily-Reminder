package com.onedevapps.dailyreminder.dataBase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule_table")
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true) val itemId: Int = 0,
    val title: String,
    val date: String,
    val time: String
)