package com.fps69.myapplication.Fragment

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fps69.myapplication.Adaptar.CartAdapter
import com.fps69.myapplication.Adaptar.PopularAdapter
import com.fps69.myapplication.ApiDummyDataInterface
import com.fps69.myapplication.DummeyUserData
import com.fps69.myapplication.PayOutActivity
import com.fps69.myapplication.R
import com.fps69.myapplication.RecipeDummyUserData
import com.fps69.myapplication.databinding.FragmentCartBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class cartFragment() : Fragment() {

    lateinit var binding: FragmentCartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)


        InitRetrofil()



        binding.proccedButtonCart.setOnClickListener {
            val intent =Intent(requireContext(),PayOutActivity::class.java)
            startActivity(intent)
        }




        return binding.root
    }


    fun InitRetrofil() {

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



                InitRecyclerView(productList)


            }

            override fun onFailure(p0: Call<DummeyUserData?>, p2: Throwable) {
                // If API call fails
                Log.d("main Activity ", "Error happen" + p2.message)
            }


        })

    }


    fun InitRecyclerView(productList: List<RecipeDummyUserData>) {

        binding.cartrecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CartAdapter(productList)

        }


    }
}