
package com.example.mhiru.ui.conversations

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mhiru.R
import com.example.mhiru.databinding.FragmentChatListBinding
import com.example.mhiru.databinding.FragmentConversationListBinding
import com.example.mhiru.ui.ChatItem
import com.example.mhiru.ui.ChatListAdapter
import com.example.test.ChatBubble
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConversationsListFragment : Fragment() {

    private var _binding: FragmentConversationListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_chat_list, container, false)
        val toolbar = requireActivity().findViewById<Toolbar>(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)



        _binding = FragmentConversationListBinding.inflate(inflater, container, false)

        val recyclerview=root.findViewById<RecyclerView>(R.id.rvchat_list)

        var chatsAdapter = ChatListAdapter(mutableListOf())
        recyclerview.adapter=chatsAdapter
        recyclerview.layoutManager= LinearLayoutManager(context)

        //test if chatList interface works
        val item1= ChatItem("Ronald Mattews","Yesterday")
        val item2= ChatItem("Sarah Mcollins","10/05/2023")
        val item3= ChatItem("Dave Simons","11/05/2023")
        val item4= ChatItem("Dave Lampard","9/05/2023")
        val item5= ChatItem("Russel Simons","11/02/2023")
        val item6= ChatItem("Gordon Silver","23/12/2022")
        val item7= ChatItem("Adam Silver","13/12/2022")
        val item8= ChatItem("Glenn Powell","23/11/2022")

        //val item4= ChatItem("Cristiano Ronaldo","08/05/2020")

        chatsAdapter.addItem(item1)
        chatsAdapter.addItem(item2)
        chatsAdapter.addItem(item3)
        chatsAdapter.addItem(item4)
        chatsAdapter.addItem(item5)
        chatsAdapter.addItem(item6)
        chatsAdapter.addItem(item7)
        chatsAdapter.addItem(item8)

        return root

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)

        // Do something with myMenuItem
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}