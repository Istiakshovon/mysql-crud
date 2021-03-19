package com.example.mysql_crud

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mysql_crud.model.Contacts

class Adapter(val context: Context, val contacts : List<Contacts>) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_item,parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: Adapter.MyViewHolder, position: Int) {
        val contacts = contacts[position]
        holder.setData(contacts,position)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var currentContact: Contacts? = null
        var currentPosition: Int = 0

        init {
            itemView.setOnClickListener {
                val intent: Intent = Intent(context,EditorActivity::class.java)
                intent.putExtra("id",currentContact?.getId())
                intent.putExtra("name",currentContact?.getName())
                intent.putExtra("email",currentContact?.getEmail())
                context.startActivity(intent)
            }
        }

        fun setData(contacts: Contacts, position: Int) {
            val name = itemView.findViewById<TextView>(R.id.name)
            val email = itemView.findViewById<TextView>(R.id.email)

            name.text = contacts?.getCell()
            email.text = contacts?.getEmail()

            currentContact = contacts
            currentPosition = position
        }

    }

}