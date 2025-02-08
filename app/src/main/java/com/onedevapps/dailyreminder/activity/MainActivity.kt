package com.onedevapps.dailyreminder.activity

import android.app.Application
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.onedevapps.dailyreminder.DataModels.ScheduleModels
import com.onedevapps.dailyreminder.R
import com.onedevapps.dailyreminder.ViewModels.MainViewModel
import com.onedevapps.dailyreminder.adapter.ScheduleListAdapter
import com.onedevapps.dailyreminder.databinding.ActivityMainBinding
import com.onedevapps.dailyreminder.swipeGuesture.SwipeToDeleteCallBack

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val viewModel by lazy { MainViewModel(Application()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""



        binding.scheduleListRecyclerView.layoutManager = LinearLayoutManager(this)

        val scheduleList = mutableListOf<ScheduleModels>()


        var adapter = ScheduleListAdapter(scheduleList,binding.scheduleListRecyclerView,viewModel)
        binding.scheduleListRecyclerView.adapter = adapter

        scheduleList.add(ScheduleModels("WaterTake", "14/06/2023", "11:10 AM"))
        scheduleList.add(ScheduleModels("Take Medicine", "14/06/2023", "11:50 AM"))
        scheduleList.add(ScheduleModels("Wish BirthDay ", "15/06/2023", "11:10 AM"))
        scheduleList.add(ScheduleModels("Go To Office", "15/06/2023", "11:10 PM"))
        scheduleList.add(ScheduleModels("Wash Cloth", "16/06/2023", "1:10 AM"))
        scheduleList.add(ScheduleModels("Take Juice", "21/06/2023", "11:10 PM"))
        scheduleList.add(ScheduleModels("Need Break", "12/07/2023", "09:10 AM"))
        scheduleList.add(ScheduleModels("Chill Time", "10/07/2023", "1:30 AM"))
        scheduleList.add(ScheduleModels("WaterTake", "11/07/2023", "11:10 AM"))
        scheduleList.add(ScheduleModels("Lunch Time", "11/07/2023", "11:10 AM"))
        scheduleList.add(ScheduleModels("Work Time", "11/07/2023", "11:10 AM"))
        scheduleList.add(ScheduleModels("WaterTake", "11/07/2023", "11:10 AM"))
        scheduleList.add(ScheduleModels("Bed Time", "11/07/2023", "11:10 AM"))

        adapter.notifyDataSetChanged()

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallBack(adapter))
        itemTouchHelper.attachToRecyclerView( binding.scheduleListRecyclerView)

        viewModel.listCount.value = scheduleList.size


        viewModel.listLiveData.observe(this){
            if (it != null) {
                binding.textCountReminders.text = it.toString() + " reminders coming up"
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        if (id == R.id.threeDot) {

            Toast.makeText(this, "More Clicked", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.menuPlus) {

            Toast.makeText(this, "Plus Clicked", Toast.LENGTH_SHORT).show()
        } else if (id == R.id.app_bar_search) {

            Toast.makeText(this, "Plus Clicked", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}