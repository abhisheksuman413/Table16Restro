package com.fps69.myapplication.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.fps69.myapplication.Adaptar.MenuAdapter
import com.fps69.myapplication.Adaptar.PopularAdapter
import com.fps69.myapplication.ApiDummyDataInterface
import com.fps69.myapplication.DataClass.MenuItems
import com.fps69.myapplication.DummeyUserData
import com.fps69.myapplication.R
import com.fps69.myapplication.RecipeDummyUserData
import com.fps69.myapplication.databinding.FragmentMenuBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MenuBottomSheetFragment : BottomSheetDialogFragment() {

    // var for Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private var menuItem: ArrayList<MenuItems> = ArrayList()

    private lateinit var binding: FragmentMenuBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().reference
        database = FirebaseDatabase.getInstance()

        binding = FragmentMenuBottomSheetBinding.inflate(inflater, container, false)
        binding.backbuttonmenu.setOnClickListener {
            dismiss()
        }

        retrieveMenuItems()
//        InitRetrofil()
        return binding.root
    }

    private fun retrieveMenuItems() {
        val foodRef = database.reference.child("menu")
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Clear data before insert
                menuItem.clear()
                for (items in snapshot.children) {
                    val dummyUser = items.getValue(MenuItems::class.java)
                    dummyUser?.let {
                        menuItem.add(it)
                    }
                }
                InitRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    requireContext(),
                    " Cannot Show item Internal Error ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

//    private fun InitRetrofil() {
//        val retrofitBuilder = Retrofit.Builder()
//            .baseUrl("https://dummyjson.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiDummyDataInterface::class.java)
//
//
//        val retrofilData =  retrofitBuilder.getProductData()
//
//        retrofilData.enqueue(object: Callback<DummeyUserData?> {
//            override fun onResponse(p0: Call<DummeyUserData?>, p1: Response<DummeyUserData?>) {
//                // If Api call is success
//                val responseBody =p1.body()
//                val productList = responseBody?.recipes!!
//
//
//
//                InitRecyclerView(productList)
//
//
//            }
//
//            override fun onFailure(p0: Call<DummeyUserData?>, p2: Throwable) {
//                // If API call fails
//                Log.d("main Activity ","Error happen"+ p2.message)
//            }
//
//
//        })
//    }

    private fun InitRecyclerView() {

        binding.menuRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = MenuAdapter(menuItem, requireContext())
        }


    }


}