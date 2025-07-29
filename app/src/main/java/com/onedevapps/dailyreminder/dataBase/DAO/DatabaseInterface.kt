package com.onedevapps.dailyreminder.dataBase.DAO

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.onedevapps.dailyreminder.dataBase.entity.ScheduleEntity
import com.onedevapps.dailyreminder.dataBase.entity.DatabaseHistoryEntity
import kotlinx.coroutines.flow.Flow

interface DatabaseInterface {


    @Insert
    suspend fun insertScheduleData(data: ScheduleEntity)

    @Update
    suspend fun updateData(data: ScheduleEntity)


    @Query("SELECT * FROM schedule_table_history")
    suspend fun getAllHistoryData(): Flow<List<DatabaseHistoryEntity>>

    @Query("SELECT * FROM schedule_table_history WHERE id = :id")
    suspend fun getHistoryDataById(id: Int): LiveData<DatabaseHistoryEntity>

    @Query("SELECT * FROM schedule_table")
    fun getAllScheduleData(): Flow<List<ScheduleEntity>>

    @Query("SELECT * FROM schedule_table WHERE itemId = :id")
    suspend fun getDataById(id: Int): LiveData<ScheduleEntity>

    @Query("DELETE FROM schedule_table WHERE itemId = :id")
    suspend fun deleteData(id: Int)

}