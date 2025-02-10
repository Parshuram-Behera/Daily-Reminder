package com.onedevapps.dailyreminder.adapter

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

class ScheduleListAdapter(
    private var fullList: MutableList<ScheduleModels>, // Stores the original unfiltered list
    private var filteredList: MutableList<ScheduleModels>, // Stores the currently displayed list
    private var recyclerView: RecyclerView,
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<ScheduleListAdapter.ListViewHolder>() {

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var scheduleTitle: TextView = view.findViewById(R.id.scheduleTitle)
        var scheduleDate: TextView = view.findViewById(R.id.scheduleDate)
        var scheduleTime: TextView = view.findViewById(R.id.scheduleTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.schedule_item_view, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = filteredList[position]

        with(holder) {
            scheduleTitle.text = item.scheduleTitle
            scheduleDate.text = item.scheduleDate
            scheduleTime.text = item.scheduleTime
        }
    }

    fun removeItem(position: Int) {
        if (position in 0 until filteredList.size) {
            val removedItem = filteredList.removeAt(position)
            fullList.remove(removedItem) // Remove from original list too
            notifyItemRemoved(position)

            Handler(Looper.getMainLooper()).post {
                notifyItemRangeChanged(position, filteredList.size)
                recyclerView.requestLayout()
                viewModel.listCount.value = fullList.size
            }
        }
    }

    fun updateList(newList: List<ScheduleModels>) {
        filteredList.clear()
        filteredList.addAll(newList)
        notifyDataSetChanged()
    }

    fun restoreFullList() {
        filteredList.clear()
        filteredList.addAll(fullList)
        notifyDataSetChanged()
    }
}
