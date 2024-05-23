package com.fps69.myapplication.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.collection.ArrayMap

import com.fps69.myapplication.Adaptar.MenuAdapter
import com.fps69.myapplication.ApiDummyDataInterface
import com.fps69.myapplication.DummeyUserData
import com.fps69.myapplication.RecipeDummyUserData

import com.fps69.myapplication.databinding.FragmentSearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchFragment : Fragment() {

    lateinit var binding:FragmentSearchBinding
    lateinit var  Tempadapter:MenuAdapter
    val bkgg:array







    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentSearchBinding.inflate(inflater,container,false)

        val TempproductList = InitRetrofil()
        var name : array



        for(index in TempproductList){
        }

        return binding.root
    }


    private fun InitRetrofil(): List<RecipeDummyUserData> {

        lateinit var productList: List<RecipeDummyUserData>

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiDummyDataInterface::class.java)


        val retrofilData =  retrofitBuilder.getProductData()

        retrofilData.enqueue(object: Callback<DummeyUserData?> {
            override fun onResponse(p0: Call<DummeyUserData?>, p1: Response<DummeyUserData?>) {
                // If Api call is success
                val responseBody =p1.body()
                 productList = responseBody?.recipes!!

            }

            override fun onFailure(p0: Call<DummeyUserData?>, p2: Throwable) {
                // If API call fails
                Log.d("main Activity ","Error happen"+ p2.message)
            }


        })

        return productList

    }
}