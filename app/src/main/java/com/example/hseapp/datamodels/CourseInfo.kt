package com.example.hseapp.datamodels

import com.google.gson.annotations.SerializedName

data class CourseInfo(
    @SerializedName("course_description") var description:String,
    var grading:String,
    var teachers: ArrayList<Teacher>,
    @SerializedName("chats") var chatlist: ArrayList<ChatInfo>
    )