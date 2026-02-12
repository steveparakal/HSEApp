package com.example.hseapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hseapp.R
import com.example.hseapp.databinding.ActivityChatBinding
import com.example.hseapp.datamodels.ChatInfo
import com.example.hseapp.datamodels.ChatMessage
import com.example.hseapp.ui.adapters.ChatAdapter
import com.example.hseapp.ui.adapters.StudyUnitAdapter
import com.example.hseapp.utils.Constants
import com.example.hseapp.viewmodels.ChatViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ChatActivity : AppCompatActivity() {
    lateinit var chatAdapter: ChatAdapter
    lateinit var binding:ActivityChatBinding
    private val viewmodel by viewModels<ChatViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_chat)
        binding.chatToolbar.backButton.setOnClickListener {
            onBackPressed()
        }
        val chat: ChatInfo = intent.getSerializableExtra(Constants.CHAT_INFO) as ChatInfo
        binding.chatToolbar.toolbarTitle.text = chat.chatname

        initView()
        observeLiveData()
        viewmodel.getChat()

    }

    fun initView() {
        binding.chat.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)
        chatAdapter = ChatAdapter(null)
        binding.chat.adapter = chatAdapter
        binding.messageBar.upload.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        if(binding.messageBar.textbox.text.isNotEmpty()) {
            var chatmessage = ChatMessage(binding.messageBar.textbox.text.toString(), "", "Self", getCurrentTime())
            if(chatAdapter.chatmessage != null) {
                chatAdapter.chatmessage?.add(chatmessage)
            }
            else {
                var chatlist = ArrayList<ChatMessage>()
                chatlist.add(chatmessage)
                chatAdapter.chatmessage = chatlist
            }

            chatAdapter.notifyDataSetChanged()
            binding.messageBar.textbox.text.clear()
        }
    }

    private fun getCurrentTime(): String {
        val presentTime_Date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("HH:mm")
        return dateFormat.format(presentTime_Date)
    }

    private fun observeLiveData() {
        viewmodel.chatData.observe(this) {
            chatAdapter.chatmessage = it
            chatAdapter.notifyDataSetChanged()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}