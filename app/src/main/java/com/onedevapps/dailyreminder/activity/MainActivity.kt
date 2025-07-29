package com.onedevapps.dailyreminder.activity

import android.app.Application
import android.content.res.Configuration
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.onedevapps.dailyreminder.dataModels.ScheduleItem
import com.onedevapps.dailyreminder.R
import com.onedevapps.dailyreminder.viewModels.MainViewModel
import com.onedevapps.dailyreminder.adapter.ScheduleListAdapter
import com.onedevapps.dailyreminder.databinding.ActivityMainBinding
import com.onedevapps.dailyreminder.swipeGuesture.SwipeToDeleteCallBack

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val viewModel by lazy { MainViewModel(Application()) }

    private var filteredList: MutableList<ScheduleItem> = mutableListOf()
    private var scheduleList = mutableListOf<ScheduleItem>()


    lateinit var  adapter : ScheduleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""

        // Initialize adapter with original and filtered lists
        adapter = ScheduleListAdapter(
            fullList = viewModel.scheduleList.value?.toMutableList() ?: mutableListOf(),
            filteredList = viewModel.scheduleList.value?.toMutableList() ?: mutableListOf(),
            recyclerView = binding.scheduleListRecyclerView,
            viewModel = viewModel
        )

        binding.scheduleListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.scheduleListRecyclerView.adapter = adapter

        // Observe changes in filteredList and update adapter
        viewModel.filteredList.observe(this) { newList ->

            if (!newList.isNullOrEmpty()){
                adapter.updateList(newList.toMutableList())
            }
        }



        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallBack(adapter))
        itemTouchHelper.attachToRecyclerView(binding.scheduleListRecyclerView)

        viewModel.listLiveData.observe(this){
            if (it != null) {
                binding.textCountReminders.text = it.toString() + " reminders coming up"
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        // changing the toolbar icon colour as the app theme mode
        var iconColor = 0
        var currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> {
                Log.d("Theme", " Light Theme ")
                iconColor = getColor(R.color.black)
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                Log.d("Theme", "Dark Theme")

               iconColor = getColor(R.color.white)
            }
        }
        menu?.let {
            for (i in 0 until it.size()) {
                val menuItem = it.getItem(i)
                menuItem.icon?.let { icon ->
                    tintMenuIcon(icon, iconColor)
                }
            }
        }

        // listening the text inputted in the search menu of tool bar

        var searchIcon = menu?.findItem(R.id.app_bar_search)
        var searchView = searchIcon?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.filterList(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    adapter.restoreFullList() // Restore original list when search is cleared
                } else {
                    viewModel.filterList(newText)
                }
                return true
            }
        })
        return true
    }
    private fun tintMenuIcon(icon: Drawable, color: Int) {
        val wrappedIcon = DrawableCompat.wrap(icon)
        DrawableCompat.setTint(wrappedIcon, color)
        wrappedIcon.setTintMode(PorterDuff.Mode.SRC_IN)
    }

  /*  private fun filterList(query: String?) {
        filteredList.clear()

        if (query.isNullOrBlank()) {

            // Reset list when query is empty

            Log.d("Search", "Empty : $scheduleList")
            filteredList.addAll(scheduleList)
            Log.d("Search", "Empty : Filter  $filteredList")
            adapter.updateList(filteredList)
        } else {
            val searchText = query.lowercase().trim()
            filteredList.addAll(scheduleList.filter {
                it.scheduleTitle.lowercase().contains(searchText)
            })

            adapter.updateList(filteredList)
        }

        adapter.notifyDataSetChanged() // Refresh RecyclerView
    }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var id = item.itemId

        if (id == R.id.menuMoreOption) {
            menuMoreOptionClick()
        } else if (id == R.id.app_bar_search) {

            Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun menuAddTaskClick() {
        Toast.makeText(this, "Add Task Clicked", Toast.LENGTH_SHORT).show()

        /*var intent = Intent(this, AddTaskActivity::class.java)
        startActivity(intent)*/

    }

    private fun menuMoreOptionClick() {
        Toast.makeText(this, "More Clicked", Toast.LENGTH_SHORT).show()

    }
}