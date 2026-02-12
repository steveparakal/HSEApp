package com.example.hseapp.datamodels

import java.io.Serializable

data class Assignment(
    var subject:String,
    var name:String,
    var deadline: String,
    var submission: String
    ):Serializable