package com.onedevapps.dailyreminder.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {


    var listCount: MutableLiveData<Int> = MutableLiveData<Int>()

    var listLiveData: LiveData<Int> = listCount

}