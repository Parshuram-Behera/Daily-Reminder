package com.onedevapps.dailyreminder.dataBase.entity

import androidx.room.Entity

@Entity(tableName = "schedule_table_history")
data class DatabaseHistoryEntity(
    val id: Int,
    val title: String,
    val date: String,
    val time: String
)