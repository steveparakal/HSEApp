package com.example.hseapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hseapp.databinding.GradeUnitBinding
import com.example.hseapp.datamodels.StudyUnit

class StudyUnitAdapter(var names :ArrayList<StudyUnit>?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = GradeUnitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StudyUnitHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as StudyUnitHolder).bind(names!![position])
    }

    override fun getItemCount(): Int {
        return if (names != null)
            names!!.size
        else
            0
    }

    class StudyUnitHolder(val binding: GradeUnitBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(studyunit: StudyUnit) {
            binding.unit.text = studyunit.unit
            binding.grade.layoutManager = LinearLayoutManager(
                binding.root.context, LinearLayoutManager.VERTICAL,
                false
            )
                var gradeadapter = GradeAdapter(studyunit.gradelist)
                binding.grade.adapter = gradeadapter
        }
    }
}