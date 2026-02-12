package com.example.hseapp.datamodels

import com.google.gson.annotations.SerializedName

data class CalendarResponse(
    @SerializedName("schedule") var days: ArrayList<Day>
    )