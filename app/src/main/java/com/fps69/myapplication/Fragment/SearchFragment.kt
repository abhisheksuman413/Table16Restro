package com.fps69.myapplication.Fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager

import com.fps69.myapplication.Adaptar.OriginalMenuAdapter
import com.fps69.myapplication.ApiDummyDataInterface
import com.fps69.myapplication.DummeyUserData
import com.fps69.myapplication.R

import com.fps69.myapplication.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchFragment : Fragment() {

    private lateinit var binding:FragmentSearchBinding
    private lateinit var Adapter: OriginalMenuAdapter

//    private val menuFoodName= listOf("Classic Margherita Pizza",
//                                        "Vegetarian Stir-Fry",
//                                        "Chocolate Chip Cookies",
//                                        "Chicken Alfredo Pasta",
//                                        "Mango Salsa Chicken",
//                                        "Quinoa Salad with Avocado",
//                                        "Tomato Basil Bruschetta",
//                                        "Beef and Broccoli Stir-Fry",
//                                        "Caprese Salad",
//                                        "Shrimp Scampi Pasta")
//    private val menuFoodPrice= listOf("67","65","63","31","90","54","76","34","21","65")
//
//    private val menuFoodImage= listOf(R.drawable.itemimage,
//                                      R.drawable.itemimage,
//                                      R.drawable.itemimage,
//                                      R.drawable.itemimage,
//                                      R.drawable.itemimage,
//                                      R.drawable.itemimage,
//                                      R.drawable.itemimage,
//                                      R.drawable.itemimage,
//                                      R.drawable.itemimage,
//                                      R.drawable.itemimage)

    private  val menuFoodName= mutableListOf<String>()
    private val menuFoodPrice= mutableListOf<String>()
    private val menuFoodImage= mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }




    private  val filtermenuFoodName= mutableListOf<String>()
    private val filtermenuFoodPrice= mutableListOf<String>()
    private val filtermenuFoodImage= mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentSearchBinding.inflate(inflater,container,false)

        Adapter= OriginalMenuAdapter(filtermenuFoodName,
            filtermenuFoodPrice, filtermenuFoodImage)
        binding.recyclerViewSearch.layoutManager=LinearLayoutManager(requireContext())
        binding.recyclerViewSearch.adapter=Adapter


        // Setup For Search View
        //Starting me yha pe kiye the fir Retrofit ke andr kiye


        // Show all menu Item
        //Starting me yha pe kiye the fir Retrofit ke andr kiye


        InitRetrofil()

        return binding.root
    }

    private fun InitRetrofil() {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiDummyDataInterface::class.java)


        val retrofilData = retrofitBuilder.getProductData()

        retrofilData.enqueue(object : Callback<DummeyUserData?> {
            override fun onResponse(p0: Call<DummeyUserData?>, p1: Response<DummeyUserData?>) {
                // If Api call is success
                val responseBody = p1.body()
                val productList = responseBody?.recipes!!

                for(index in productList.indices){
                    menuFoodName.add("${productList[index].name}")
                    menuFoodPrice.add("${productList[index].prepTimeMinutes.toString()}")
                    menuFoodImage.add("${productList[index].image}")
                }


                Adapter= OriginalMenuAdapter(filtermenuFoodName,
                    filtermenuFoodPrice, filtermenuFoodImage)
                binding.recyclerViewSearch.layoutManager=LinearLayoutManager(requireContext())
                binding.recyclerViewSearch.adapter=Adapter


                setupSearchView()
                showAllMenu()






            }

            override fun onFailure(p0: Call<DummeyUserData?>, p2: Throwable) {
                // If API call fails
                Log.d("main Activity ", "Error happen" + p2.message)
            }


        })
    }

    private fun showAllMenu() {
        filtermenuFoodName.clear()
        filtermenuFoodPrice.clear()
        filtermenuFoodImage.clear()


        filtermenuFoodName.addAll(menuFoodName)
        filtermenuFoodPrice.addAll(menuFoodPrice)
        filtermenuFoodImage.addAll(menuFoodImage)

        Adapter.notifyDataSetChanged()

    }


    private fun setupSearchView() {
        binding.searchViewSearch.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
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

        filtermenuFoodName.clear()
        filtermenuFoodPrice.clear()
        filtermenuFoodImage.clear()

        menuFoodName.forEachIndexed { index, foodnamee ->
            if(foodnamee.contains(query,ignoreCase = true)){
                filtermenuFoodName.add(foodnamee)
                filtermenuFoodPrice.add(menuFoodPrice[index])
                filtermenuFoodImage.add(menuFoodImage[index])
            }

        }
        Adapter.notifyDataSetChanged()


    }


}