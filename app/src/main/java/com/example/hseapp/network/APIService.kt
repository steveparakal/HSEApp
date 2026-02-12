package com.example.hseapp.network

import com.example.hseapp.datamodels.*
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
     @GET("qewel435s0shmdv/Timetable.json?dl=0")
     suspend fun getTimetable() : Response<CalendarResponse>

     @GET("opoj1mffkzkx6cw/Assignments.json?dl=0")
     suspend fun getAssignments() : Response<CalendarResponse>

     @GET("u4vk4l8gtf9eghh/Courses.json?dl=0")
     suspend fun getCourses() : Response<ArrayList<Course>>

     @GET("i3b60ujk4zvueu7/Grades.json?dl=0")
     suspend fun getGrades() : Response<GradeResponse>

     @GET("std13o92ucj17vx/User.json?dl=0")
     suspend fun getUsers() : Response<ArrayList<User>>

     @GET("pq1st2q81yjcydu/Chats.json?dl=0")
     suspend fun getChats() : Response<ChatMessageResponse>
}