package com.example.hseapp.datamodels

import java.io.Serializable

data class ChatInfo(
    var chatname: String,
    var lastsender: String,
    var lastmessage: String,
    var count: Int,
    var imgurl: String
    ):Serializable