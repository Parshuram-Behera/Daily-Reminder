package com.onedevapps.dailyreminder.adapter

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.onedevapps.dailyreminder.DataModels.ScheduleModels
import com.onedevapps.dailyreminder.R
import com.onedevapps.dailyreminder.ViewModels.MainViewModel

class ScheduleListAdapter(var scheduleList: MutableList<ScheduleModels>,var recyclerView: RecyclerView,val viewModel :MainViewModel) :
    RecyclerView.Adapter<ScheduleListAdapter.listViewHolder>() {



    class listViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var scheduleTitle: TextView = view.findViewById(R.id.scheduleTitle)
        var scheduleDate: TextView = view.findViewById(R.id.scheduleDate)
        var scheduleTime: TextView = view.findViewById(R.id.scheduleTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listViewHolder {

        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.schedule_item_view, parent, false)

        return listViewHolder(view)
    }

    override fun getItemCount(): Int {

        return scheduleList.size
    }

    override fun onBindViewHolder(holder: listViewHolder, position: Int) {

        var item = scheduleList[position]

        with(holder) {
            scheduleTitle.text = item.scheduleTitle
            scheduleDate.text = item.scheduleDate
            scheduleTime.text = item.scheduleTime
        }
    }

    fun removeItem(position: Int) {
        if (position in 0 until scheduleList.size) {
            scheduleList.removeAt(position)
            notifyItemRemoved(position)


            Handler(Looper.getMainLooper()).post {
                notifyItemRangeChanged(position, scheduleList.size)
                recyclerView.requestLayout()
                viewModel.listCount.value = scheduleList.size
            }
        }
    }
}
