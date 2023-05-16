package com.example.mhiru

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
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
        setContentView(binding.root)

        val chatListView = binding.include.chatListView
        val chatBubbles = ArrayList<ChatBubble>()
        val adapter = ChatAdapter(this.layoutInflater, chatBubbles)
        chatListView.adapter = adapter


        val chatBubble = ChatBubble("Hello?", false,"Yesterday")
        chatBubbles.add(chatBubble)
        adapter.notifyDataSetChanged()
        chatListView.smoothScrollToPosition(chatBubbles.size - 1)
        binding.imageButton2.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            intent.putExtra("key","client")
            startActivity(intent)
        }
        val sendButton=binding.include.chatSendButton
        sendButton.setOnClickListener {
            val message = binding.include.chatInputField.text.toString()
            val messageEditText = binding.include.chatInputField

            if (!TextUtils.isEmpty(message)) {

                val current = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd HH:mm"))
                    .toString()


                val chatBubble = ChatBubble(message, true, current)
                chatBubbles.add(chatBubble)
                adapter.notifyDataSetChanged()
                chatListView.smoothScrollToPosition(chatBubbles.size - 1)
                messageEditText.setText("")
            }
        }
    }
}