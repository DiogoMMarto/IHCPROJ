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
import androidx.core.content.ContextCompat
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

    private var eMode = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentChatBotBinding.inflate(inflater, container, false)
        val chatBotViewModel = ViewModelProvider(this)[ChatBotViewModel::class.java]
        // Use the null-check operator (?.) to safely access `binding`
        val root: View = binding.root
        val chatView: ChatBinding = binding.chatView

        val chatListView = chatView.chatListView
        val chatBubbles = ArrayList<ChatBubble>()
        val adapter = ChatAdapter(inflater, chatBubbles)
        chatListView.adapter = adapter

        val emergencyMode = binding.Emergencybutton

        emergencyMode.setOnClickListener{
            if(!eMode){
                binding.Emergencybutton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.red_100))
                binding.Emergencybutton.setBackgroundResource(R.drawable.textbox)
                chatListView.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.light_red))
                binding.chatView.chatInputLayout.setBackgroundColor(resources.getColor(R.color.light_red))

                val current = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd HH:mm")).toString()
                val chatBubble = ChatBubble("Entered emergency mode", false,current)
                chatBubbles.add(chatBubble)
                adapter.notifyDataSetChanged()
                chatListView.smoothScrollToPosition(chatBubbles.size - 1)


                thread {
                    server.eModeON()
                }


                eMode = true
            } else {
                binding.Emergencybutton.setColorFilter(ContextCompat.getColor(requireContext(), R.color.white))
                binding.Emergencybutton.setBackgroundResource(R.drawable.emergencybutton)
                chatListView.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.white))
                binding.chatView.chatInputLayout.setBackgroundColor(resources.getColor(R.color.white))


                val current = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd HH:mm")).toString()
                val chatBubble = ChatBubble("Exited emergency mode", false,current)
                chatBubbles.add(chatBubble)
                adapter.notifyDataSetChanged()
                chatListView.smoothScrollToPosition(chatBubbles.size - 1)


                thread {
                    server.eModeOff()
                }
                eMode = false
            }
        }

        val sendButton = chatView.chatSendButton
        val messageEditText = chatView.chatInputField


        val chatBubble = ChatBubble("Hello, I'm Empathy", false,LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd HH:mm")).toString())
        chatBubbles.add(chatBubble)
        adapter.notifyDataSetChanged()

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