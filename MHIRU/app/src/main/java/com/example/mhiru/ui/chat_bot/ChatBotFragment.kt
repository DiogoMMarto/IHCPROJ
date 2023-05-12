package com.example.mhiru.ui.chat_bot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mhiru.R
import com.example.mhiru.databinding.FragmentChatBotBinding
import com.google.android.material.navigation.NavigationView

class ChatBotFragment : Fragment() {

    private var _binding: FragmentChatBotBinding? = null
    private val binding get() = _binding!!

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
        val textView: TextView = binding.textGallery
        chatBotViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
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