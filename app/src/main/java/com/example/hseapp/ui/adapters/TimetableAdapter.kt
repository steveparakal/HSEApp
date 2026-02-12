package com.example.hseapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hseapp.databinding.CalendarTimetableBinding
import com.example.hseapp.datamodels.Timetable


class TimetableAdapter(var names: ArrayList<Timetable>?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =  CalendarTimetableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimetableHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TimetableHolder).bind(names!![position])
    }

    override fun getItemCount(): Int {
        return if (names != null)
            names!!.size
        else
            0
    }

    class TimetableHolder(val binding: CalendarTimetableBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(timetable: Timetable) {
            binding.start.text = timetable.start
            binding.end.text = timetable.end
            binding.mode.text = timetable.mode
            binding.name.text = timetable.title
            binding.type.text = timetable.type

        }
    }
}