package com.example.hseapp.datamodels

import com.google.gson.annotations.SerializedName

data class Course(
    var name:String,
    var msgCount:String,
    @SerializedName("course_info") var courseInfo: CourseInfo,
    var isSelected:Boolean = false
    )