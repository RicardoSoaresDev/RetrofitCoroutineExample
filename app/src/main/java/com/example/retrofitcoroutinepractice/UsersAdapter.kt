package com.example.retrofitcoroutinepractice

import android.graphics.ColorSpace.Model
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitcoroutinepractice.model.Data

class UsersAdapter(val listUsers: List<Data>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = listUsers[position]
        when (holder) {
            is UserViewHolder -> {
                holder.bind(user)
            }
        }
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    class UserViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

        private val userId = itemview.findViewById<TextView>(R.id.userId)
        private val name = itemview.findViewById<TextView>(R.id.name)
        private val email = itemview.findViewById<TextView>(R.id.email)
//        private val avatar = itemview.findViewById<ImageView>(R.id.avatarImage)

        fun bind(user: Data) {
            userId.text = user.id.toString()
            name.text = user.firstName + " " + user.lastName
            email.text = user.email


        }

    }
}