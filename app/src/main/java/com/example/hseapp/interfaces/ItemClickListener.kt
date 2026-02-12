package com.example.hseapp.interfaces

import com.example.hseapp.datamodels.Assignment
import com.example.hseapp.datamodels.Course
import com.example.hseapp.datamodels.Period

interface ItemClickListener {
    fun onCourseClick(obj: Course)
    fun onPeriodClick(obj: Period)
    fun onAssignmentClick(obj: Assignment)
}