package com.example.hseapp.datamodels

import com.google.gson.annotations.SerializedName

data class GradeResponse(
    @SerializedName("grades") var grades : ArrayList<StudyUnit>
    )