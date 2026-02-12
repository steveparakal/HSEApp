package com.example.hseapp.datamodels

import com.google.gson.annotations.SerializedName

data class ChatMessageResponse(
    @SerializedName("studentsonly") var chat: ArrayList<ChatMessage>
)