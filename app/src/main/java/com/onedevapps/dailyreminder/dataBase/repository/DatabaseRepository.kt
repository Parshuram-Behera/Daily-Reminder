package com.onedevapps.dailyreminder.dataBase.repository

import com.onedevapps.dailyreminder.dataBase.DAO.DatabaseInterface
import com.onedevapps.dailyreminder.dataBase.entity.ScheduleEntity
import com.onedevapps.dailyreminder.dataModels.ScheduleItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DatabaseRepository(private val databaseDao: DatabaseInterface) {



    suspend fun addSchedule(scheduleItem: ScheduleEntity){
        withContext (Dispatchers.IO){
            databaseDao.insertScheduleData(scheduleItem)
        }
    }


    fun getAllScheduleData(): Flow<List<ScheduleEntity>> = databaseDao.getAllScheduleData()
}