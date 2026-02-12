package com.example.hseapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hseapp.databinding.GradeInfoBinding
import com.example.hseapp.datamodels.Grade


class GradeAdapter(var names: ArrayList<Grade>?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =  GradeInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GradeHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GradeHolder).bind(names!![position])
    }

    override fun getItemCount(): Int {
        return if (names != null)
            names!!.size
        else
            0
    }

    class GradeHolder(val binding: GradeInfoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(grade: Grade) {
            binding.grade = grade
        }
    }
}