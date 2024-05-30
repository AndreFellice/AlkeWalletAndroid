package com.example.alkewalletandroid.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alkewalletandroid.R

class NotificationAdapter(private val notifications: List<com.example.alkewalletandroid.model.Notification>) :
RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    inner class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.notificationTitle)
        val message: TextView = itemView.findViewById(R.id.notificationMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val notification = notifications[position]
        holder.title.text = notification.title
        holder.message.text = notification.message

        // Adding content descriptions for accessibility
        holder.title.contentDescription = "Título de la notificación: ${notification.title}"
        holder.message.contentDescription = "Mensaje de la notificación: ${notification.message}"
    }

    override fun getItemCount(): Int {
        return notifications.size
    }
}