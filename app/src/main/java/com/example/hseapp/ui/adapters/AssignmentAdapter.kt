package com.example.hseapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hseapp.databinding.CalendarAssignmentBinding
import com.example.hseapp.datamodels.Assignment
import com.example.hseapp.interfaces.ItemClickListener
import com.example.hseapp.ui.fragments.CalendarFragment


class AssignmentAdapter(var names: ArrayList<Assignment>?,val obj: ItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =  CalendarAssignmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssignmentHolder(binding, obj)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AssignmentHolder).bind(names!![position])
    }

    override fun getItemCount(): Int {
        return if (names != null)
            names!!.size
        else
            0
    }

    class AssignmentHolder(val binding: CalendarAssignmentBinding, val obj: ItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(assignment: Assignment) {
            binding.assignment = assignment

            binding.root.setOnClickListener {
                obj.onAssignmentClick(assignment)
            }

        }
    }
}