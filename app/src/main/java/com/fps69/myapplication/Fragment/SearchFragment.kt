package com.fps69.myapplication.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.fps69.myapplication.Adaptar.MenuAdapter

import com.fps69.myapplication.DataClass.MenuItems

import com.fps69.myapplication.databinding.FragmentSearchBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SearchFragment : Fragment() {

    // var for Firebase

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference

    private var itemList: ArrayList<MenuItems> = ArrayList()

    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {


        binding = FragmentSearchBinding.inflate(inflater, container, false)


        database = FirebaseDatabase.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference

        // function to retrieve data from Firebase
        retrievemenuItem()
        // function For Search View
        setupSearchView()
        return binding.root
    }

    private fun retrievemenuItem() {
        val foodRef = database.reference.child("menu")
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Clear data before insert
                itemList.clear()
                for (items in snapshot.children) {
                    val dummeyData = items.getValue(MenuItems::class.java)
                    dummeyData?.let {
                        itemList.add(dummeyData)
                    }
                }
                showAllMenu()
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

    // Show all menu Item
    private fun showAllMenu() {
        val filteritemList = ArrayList(itemList)
        filteritemList.clear()
        filteritemList.addAll(itemList)
        setupRecyclerView(filteritemList)
    }


    // Setup For Search View
    private fun setupSearchView() {
        binding.searchViewSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                filterMenuItem(query)

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterMenuItem(newText)
                return true

            }

        })
    }

    private fun filterMenuItem(query: String) {
        val filteritemList: List<MenuItems> = itemList.filter {
            it.foodName?.contains(query, ignoreCase = true) == true
        }
        setupRecyclerView(filteritemList)
    }


    private fun setupRecyclerView(filteritemList: List<MenuItems>) {
        adapter = MenuAdapter(filteritemList, requireContext())
        binding.recyclerViewSearch.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewSearch.adapter = adapter
    }

}


