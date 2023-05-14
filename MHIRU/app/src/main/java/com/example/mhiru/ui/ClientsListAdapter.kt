package com.example.mhiru.ui;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mhiru.R
import org.w3c.dom.Text

class ClientsListAdapter(
    private val items:MutableList<ChatItem>
):RecyclerView.Adapter<ClientsListAdapter.ItemsViewHolder>()
{
    class ItemsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return  ItemsViewHolder(
                LayoutInflater.from(parent.context).inflate(
                R.layout.client_item,
                parent,
                false
            )
        )
    }
    fun rmItem(item: ChatItem){

        items.remove(item)
        notifyDataSetChanged()
    }
    fun addItem(item:ChatItem){
        items.add(item)
        notifyItemInserted(items.size-1)
    }
    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val curItem=items[position]

        holder.itemView.apply {
            findViewById<TextView>(R.id.tvTitle).text=curItem.title
            findViewById<TextView>(R.id.tvDate).text=curItem.date

        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

}
