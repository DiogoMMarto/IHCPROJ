package com.example.mhiru.ui.clients

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mhiru.MainActivity
import com.example.mhiru.R
import com.example.mhiru.databinding.FragmentClientsBinding
import com.example.mhiru.ui.ChatItem
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

        var clientsAdapter = ClientsListAdapter(this.context,mutableListOf())
        recyclerview.adapter=clientsAdapter
        recyclerview.layoutManager= LinearLayoutManager(context)

        //test if chatList interface works
        val item1= ChatItem("Mark Smith","Online")
        val item2= ChatItem("John Griffin","Last online: 2 hours ago")
        //val item3= ChatItem("Rebeca Stallone","08/05/1258")

        clientsAdapter.addItem(item1)
        clientsAdapter.addItem(item2)
        //clientsAdapter.addItem(item3)
        //clientsAdapter.addItem(item4)

        val addView = binding.addBox
        val addBtn = binding.addBox.addBtn
        val appearBtn = binding.appearBtn
        val img=binding.
        appearBtn.setOnClickListener {
            addView.root.visibility = VISIBLE
        }

        addBtn.setOnClickListener {
            addView.root.visibility = INVISIBLE
            val item3= ChatItem("Rebeca Stallone","PENDING")
            clientsAdapter.addItem(item3)
            clientsAdapter.notifyDataSetChanged()
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