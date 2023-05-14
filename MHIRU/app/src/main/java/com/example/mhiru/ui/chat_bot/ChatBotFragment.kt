package com.example.mhiru.ui.chat_bot

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mhiru.R
import com.example.mhiru.databinding.ChatBinding
import com.example.mhiru.databinding.FragmentChatBotBinding
import com.example.test.ChatAdapter
import com.example.test.ChatBubble
import com.example.test.ServerP
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.concurrent.thread

class ChatBotFragment : Fragment() {

    private var _binding: FragmentChatBotBinding? = null
    private val binding get() = _binding!!

    private var server: ServerP = ServerP()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChatBotBinding.inflate(inflater, container, false)
        val chatBotViewModel = ViewModelProvider(this).get(ChatBotViewModel::class.java)
        // Use the null-check operator (?.) to safely access `binding`
        val root: View = binding.root
        val chatView: ChatBinding = binding.chatView

        val chatListView = chatView.chatListView
        val chatBubbles = ArrayList<ChatBubble>()
        val adapter = ChatAdapter(inflater, chatBubbles)
        chatListView.adapter = adapter

        val sendButton = chatView.chatSendButton
        val messageEditText = chatView.chatInputField

        sendButton.setOnClickListener {
            val message = messageEditText.text.toString()
            if (!TextUtils.isEmpty(message)) {

                val current = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd HH:mm")).toString()

                val chatBubble = ChatBubble(message, true,current)
                chatBubbles.add(chatBubble)
                adapter.notifyDataSetChanged()
                chatListView.smoothScrollToPosition(chatBubbles.size - 1)
                messageEditText.setText("")

                val temp = ChatBubble("...",false,current)
                chatBubbles.add(temp)
                adapter.notifyDataSetChanged()
                chatListView.smoothScrollToPosition(chatBubbles.size - 1)

                thread {
                    val reply = server.chatWithEmphaty(message)
                    val current = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd HH:mm")).toString()
                    val chatBubble2 = ChatBubble(reply, false,current)
                    chatBubbles.add(chatBubble2)

                    requireActivity().runOnUiThread {
                        chatBubbles.remove(temp)
                        adapter.notifyDataSetChanged()
                        chatListView.smoothScrollToPosition(chatBubbles.size - 1)
                    }


                }
            }
        }

        return root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}