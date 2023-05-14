package com.example.test

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.mhiru.R

class ChatAdapter(private val inflater: LayoutInflater, private val chatBubbles: ArrayList<ChatBubble>) : BaseAdapter() {

    override fun getCount(): Int {
        return chatBubbles.size
    }

    override fun getItem(position: Int): Any {
        return chatBubbles[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = inflater.inflate(R.layout.chat_buble, parent, false)
            holder = ViewHolder()
            holder.time = view.findViewById(R.id.timeTextView)
            holder.messageTextView = view.findViewById(R.id.messageTextView)
            holder.gravity = view.findViewById(R.id.box)
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val chatBubble = chatBubbles[position]
        holder.messageTextView?.text = chatBubble.message
        holder.time?.text = chatBubble.time

        // Set the background color of the chat bubble based on whether it was sent or received
        if (chatBubble.isSentByUser) {
            holder.messageTextView?.setBackgroundResource(R.drawable.chatbubblereceived)
            holder.gravity?.gravity = Gravity.END
        } else {
            holder.messageTextView?.setBackgroundResource(R.drawable.chatbubble)
            holder.gravity?.gravity = Gravity.START
        }

        return view!!
    }

    private class ViewHolder {
        var messageTextView: TextView? = null
        var time: TextView? = null
        var gravity: LinearLayout? = null
    }
}

class ChatBubble(val message: String, val isSentByUser: Boolean,val time: String)

