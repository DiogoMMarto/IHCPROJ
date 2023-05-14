
package com.example.mhiru.ui.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mhiru.R
import com.example.mhiru.databinding.FragmentChatListBinding
import com.example.mhiru.ui.ChatItem
import com.example.mhiru.ui.ChatListAdapter

class ChatListFragment : Fragment() {

    private var _binding: FragmentChatListBinding? = null

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



        _binding = FragmentChatListBinding.inflate(inflater, container, false)

        val recyclerview=root.findViewById<RecyclerView>(R.id.rvchat_list)

        var chatsAdapter = ChatListAdapter(mutableListOf())
        recyclerview.adapter=chatsAdapter
        recyclerview.layoutManager= LinearLayoutManager(context)

        //test if chatList interface works
        val item1= ChatItem("Marcelo Rebelo Pinto","08/05/1914")
        val item2= ChatItem("Famous Austrian Painter","08/05/2024")
        val item3= ChatItem("Rebeca Stallone","08/05/1258")
        val item4= ChatItem("Cristiano Ronaldo","08/05/2020")

        chatsAdapter.addItem(item1)
        chatsAdapter.addItem(item2)
        chatsAdapter.addItem(item3)
        chatsAdapter.addItem(item4)

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