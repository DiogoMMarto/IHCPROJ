package com.example.mhiru.ui.conversation

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mhiru.MainActivity
import com.example.mhiru.R
import com.example.mhiru.databinding.FragmentConversationBinding
import com.example.mhiru.ui.profile.ConversationViewModel
import com.example.test.ChatAdapter
import com.example.test.ChatBubble
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConversationFragment : Fragment() {

    private var _binding: FragmentConversationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentConversationBinding.inflate(inflater, container, false)
        val profileViewModel = ViewModelProvider(this).get(ConversationViewModel::class.java)

        // Use the null-check operator (?.) to safely access `binding`
        val root: View = binding.root

        val chatListView = binding.include.chatListView
        val chatBubbles = ArrayList<ChatBubble>()
        val adapter = ChatAdapter(this.layoutInflater, chatBubbles)
        chatListView.adapter = adapter


        val chatBubble = ChatBubble("Hello?", false,"Yesterday")
        chatBubbles.add(chatBubble)
        adapter.notifyDataSetChanged()
        chatListView.smoothScrollToPosition(chatBubbles.size - 1)

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
