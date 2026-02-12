package com.example.hseapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.example.hseapp.R
import com.example.hseapp.databinding.CourselistBinding
import com.example.hseapp.datamodels.Course
import com.example.hseapp.interfaces.ItemClickListener
import com.google.android.material.card.MaterialCardView

class CourseAdapter(var names: ArrayList<Course>?, val obj: ItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = CourselistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseHolder(binding, obj)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CourseHolder).bind(names!![position])

    }

    override fun getItemCount(): Int {
        return if (names != null)
            names!!.size
        else
            0
    }

    class CourseHolder(val binding: CourselistBinding, val obj: ItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        fun bind(course: Course) {
            binding.course = course
            var cardView : MaterialCardView =  binding.root as MaterialCardView

            if(course.isSelected) {
                cardView.setCardBackgroundColor(cardView.context.getColor(R.color.courses_hse_blue))
                binding.subject.setTextColor(binding.root.context.getColor(R.color.primary_hse_blue))
            }

            else {
                cardView.setCardBackgroundColor(cardView.context.getColor(R.color.white))
                binding.subject.setTextColor(binding.root.context.getColor(R.color.not_selected))
            }
            
            binding.root.setOnClickListener {
                obj.onCourseClick(course)
            }
        }
    }
}