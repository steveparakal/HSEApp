package com.example.hseapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hseapp.databinding.ChatMessageOtherBinding
import com.example.hseapp.databinding.ChatMessageUserBinding
import com.example.hseapp.databinding.GradeInfoBinding
import com.example.hseapp.datamodels.ChatMessage
import com.example.hseapp.datamodels.Grade

class ChatAdapter(var chatmessage: ArrayList<ChatMessage>?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val SELFMESSAGE = 1
    private val OTHERMESSAGE = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == OTHERMESSAGE) {
            val binding =  ChatMessageOtherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return OtherMessageHolder(binding)
        }
        else {
            val binding =  ChatMessageUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return SelfMessageHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is OtherMessageHolder) {
            (holder as OtherMessageHolder).bind(chatmessage!![position])
        }
        else {
            (holder as SelfMessageHolder).bind(chatmessage!![position])
        }
    }

    override fun getItemCount(): Int {
        return if (chatmessage != null)
            chatmessage!!.size
        else
            0
    }

    override fun getItemViewType(position: Int): Int {
        if (chatmessage?.get(position)?.sender.equals("Self", true)) {
            return SELFMESSAGE
        }
        else {
            return OTHERMESSAGE
        }
    }

    class SelfMessageHolder(val binding: ChatMessageUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatmessage: ChatMessage) {
            binding.messageTime.text = chatmessage.time
            binding.messageContent.text = chatmessage.message
            binding.otherMessageUser.text = "Me"
        }
    }

    class OtherMessageHolder(val binding: ChatMessageOtherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatmessage: ChatMessage) {
            binding.messageTime.text = chatmessage.time
            binding.messageContent.text = chatmessage.message
            binding.otherMessageUser.text = chatmessage.sender
        }
    }
}