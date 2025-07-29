package com.onedevapps.dailyreminder.dataBase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.onedevapps.dailyreminder.dataBase.entity.ScheduleEntity
import com.onedevapps.dailyreminder.dataBase.repository.DatabaseRepository
import com.onedevapps.dailyreminder.dataModels.ScheduleItem
import kotlinx.coroutines.launch

class DatabaseViewModel(application: Application, private val repository: DatabaseRepository) :
    AndroidViewModel(application) {


    val getAllScheduleData = repository.getAllScheduleData().asLiveData()


    private fun addScheduleItem(scheduleItem: ScheduleItem) {

        viewModelScope.launch {
            repository.addSchedule(
                ScheduleEntity(
                    title = scheduleItem.scheduleTitle,
                    date = scheduleItem.scheduleDate,
                    time = scheduleItem.scheduleTime
                )
            )
        }
    }
}