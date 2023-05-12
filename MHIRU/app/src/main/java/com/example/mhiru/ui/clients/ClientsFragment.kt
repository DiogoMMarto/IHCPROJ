package com.example.mhiru.ui.clients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mhiru.R
import com.example.mhiru.databinding.FragmentClientsBinding
import com.example.mhiru.ui.ChatItem
import com.example.mhiru.ui.ChatListAdapter
import com.example.mhiru.ui.ClientsListAdapter

class ClientsFragment : Fragment() {

    private var _binding: FragmentClientsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentClientsBinding.inflate(inflater, container, false)

        // Use the null-check operator (?.) to safely access `binding`
        val root: View = binding.root
        val recyclerview=root.findViewById<RecyclerView>(R.id.rvclients_list)

        var clientsAdapter = ClientsListAdapter(mutableListOf())
        recyclerview.adapter=clientsAdapter
        recyclerview.layoutManager= LinearLayoutManager(context)

        //test if chatList interface works
        val item1= ChatItem("Marcelo Rebelo Pinto","08/05/1914")
        val item2= ChatItem("Famous Austrian Painter","08/05/2024")
        val item3= ChatItem("Rebeca Stallone","08/05/1258")
        val item4= ChatItem("Cristiano Ronaldo","08/05/2020")

        clientsAdapter.addItem(item1)
        clientsAdapter.addItem(item2)
        clientsAdapter.addItem(item3)
        clientsAdapter.addItem(item4)
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