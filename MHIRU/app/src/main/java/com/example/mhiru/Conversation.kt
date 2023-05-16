package com.example.mhiru

import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.mhiru.databinding.ChatBinding
import com.example.mhiru.databinding.ConversationBinding
import com.example.test.ChatAdapter
import com.example.test.ChatBubble
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Conversation : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ConversationBinding.inflate(layoutInflater)


        val chatListView = findViewById<ListView>(R.id.chat_list_view)
        val chatBubbles = ArrayList<ChatBubble>()
        val adapter = ChatAdapter(layoutInflater, chatBubbles)
        chatListView.adapter = adapter

        val chatBubble = ChatBubble("Hello?", false,"Yesterday")
        chatBubbles.add(chatBubble)
        adapter.notifyDataSetChanged()
        chatListView.smoothScrollToPosition(chatBubbles.size - 1)

    }
}