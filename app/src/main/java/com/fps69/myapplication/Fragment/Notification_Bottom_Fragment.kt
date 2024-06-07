





package com.fps69.myapplication.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fps69.myapplication.Adaptar.NotificationAdapter
import com.fps69.myapplication.R
import com.fps69.myapplication.databinding.FragmentNotificationBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class Notification_Bottom_Fragment : BottomSheetDialogFragment() {
    private lateinit var binding : FragmentNotificationBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNotificationBottomBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        val notificatin= listOf("Your order has been Canceled Successfully","Order has been taken by the driver","Congrats Your Order Placed")
        val notificationimage= listOf<Int>(R.drawable.sadnotification,R.drawable.carnotification,R.drawable.confirmnotification)

        val Adapter=NotificationAdapter(notificatin,notificationimage)
        binding.notificationRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        binding.notificationRecyclerView.adapter=Adapter


        binding.BackButtonNotification.setOnClickListener {
            dismiss()
        }
        return binding.root
    }
}