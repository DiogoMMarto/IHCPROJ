package com.example.mhiru

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mhiru.ui.ChatItem
import com.example.mhiru.ui.ChatListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var chatsAdapter: ChatListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_list)
        val recyclerview=findViewById<RecyclerView>(R.id.rvchat_list)

        chatsAdapter= ChatListAdapter(mutableListOf())
        recyclerview.adapter=chatsAdapter
        recyclerview.layoutManager=LinearLayoutManager(this)

        //test if chatList interface works
        val item1=ChatItem("Marcelo Rebelo Pinto","08/05/1914")
        val item2=ChatItem("Famous Austrian Painter","08/05/2024")
        val item3=ChatItem("Rebeca Stallone","08/05/1258")
        val item4=ChatItem("Cristiano Ronaldo","08/05/2020")
        chatsAdapter.addItem(item1)
        chatsAdapter.addItem(item2)
        chatsAdapter.addItem(item3)
        chatsAdapter.addItem(item4)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


}