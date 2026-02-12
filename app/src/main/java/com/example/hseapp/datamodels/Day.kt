package com.example.hseapp.datamodels

data class Day(
    var day:String,
    var classes:ArrayList<Timetable>?,
    var assignments:ArrayList<Assignment>?
    )