package com.fps69.myapplication.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fps69.myapplication.databinding.FragmentNotificationBottomBinding
import com.fps69.myapplication.databinding.NotificationItemBinding

class NotificationAdapter(private val notificationText:List<String>,
    private val notifactionImage:List<Int>):
    RecyclerView.Adapter<NotificationAdapter.NotifactionAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):NotifactionAdapterViewHolder {
        val  binding= NotificationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return NotifactionAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder:NotifactionAdapterViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return notificationText.size
    }

    inner  class NotifactionAdapterViewHolder(private val binding: NotificationItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                notificationImageView.setImageResource(notifactionImage[position].toInt())
                notificationTextView.text=notificationText[position].toString()
            }

        }

    }
}