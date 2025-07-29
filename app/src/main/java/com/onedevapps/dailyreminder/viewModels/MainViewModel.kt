package com.onedevapps.dailyreminder.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.onedevapps.dailyreminder.dataBase.DatabaseBuilder
import com.onedevapps.dailyreminder.dataBase.repository.DatabaseRepository
import com.onedevapps.dailyreminder.dataBase.viewModel.DatabaseViewModel
import com.onedevapps.dailyreminder.dataModels.ScheduleItem

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val databaseDao = DatabaseBuilder.getInstance(application).getDao()
    private val databaseRepository = DatabaseRepository(databaseDao)

    private val databaseViewModel = DatabaseViewModel(
        application,
        repository = databaseRepository
    )

    var listCount: MutableLiveData<Int> = MutableLiveData<Int>()

    var listLiveData: LiveData<Int> = listCount

    private val _scheduleList = MutableLiveData<List<ScheduleItem>>() // Full list
    val scheduleList: LiveData<List<ScheduleItem>> get() = _scheduleList

    private val _filteredList = MutableLiveData<List<ScheduleItem>>() // Filtered list
    val filteredList: LiveData<List<ScheduleItem>> get() = _filteredList

    init {
        // Initialize with  data (fetch from a DB or API here)


        databaseViewModel.getAllScheduleData.observe(application.applicationContext as LifecycleOwner) {
            _scheduleList.value = it.map {
                ScheduleItem(
                    scheduleTitle = it.title,
                    scheduleDate = it.date,
                    scheduleTime = it.time
                )
            }
        }


        // dummy data
        /* _scheduleList.value = listOf(
             ScheduleModels("WaterTake", "14/06/2023", "11:10 AM"),
             ScheduleModels("Take Medicine", "14/06/2023", "11:50 AM"),
             ScheduleModels("Wish BirthDay", "15/06/2023", "11:10 AM"),
             ScheduleModels("Go To Office", "15/06/2023", "11:10 PM"),
             ScheduleModels("Wash Cloth", "16/06/2023", "1:10 AM"),
             ScheduleModels("Take Juice", "21/06/2023", "11:10 PM"),
             ScheduleModels("Need Break", "12/07/2023", "09:10 AM"),
             ScheduleModels("Chill Time", "10/07/2023", "1:30 AM"),
             ScheduleModels("Lunch Time", "11/07/2023", "11:10 AM"),
             ScheduleModels("Work Time", "11/07/2023", "11:10 AM"),
             ScheduleModels("Bed Time", "11/07/2023", "11:10 AM")
         )*/




        _filteredList.value = _scheduleList.value

        listCount.value = _scheduleList.value?.size
    }

    fun filterList(query: String?) {
        val fullList = _scheduleList.value ?: emptyList() // Get the original list
        if (query.isNullOrBlank()) {
            _filteredList.value = fullList
        } else {
            val searchText = query.lowercase().trim()
            _filteredList.value =
                fullList.filter { it.scheduleTitle.lowercase().contains(searchText) }
        }
    }
}