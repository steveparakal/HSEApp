package com.example.hseapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hseapp.datamodels.ChatMessage
import com.example.hseapp.datamodels.ChatMessageResponse
import com.example.hseapp.network.RetrofitHelper
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {
    var chatData: MutableLiveData<ArrayList<ChatMessage>> = MutableLiveData()
    val loader: MutableLiveData<Boolean> = MutableLiveData()

    fun getChat() {

        viewModelScope.launch {
            loader.postValue(true)
            val chatResult = RetrofitHelper.getInstance().getChats()
            if (chatResult != null) {
                chatData.postValue(chatResult.body()?.chat)
                loader.postValue(false)
            }
        }
    }
}