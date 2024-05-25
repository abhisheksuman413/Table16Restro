package com.fps69.myapplication.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.fps69.myapplication.Adaptar.BuyAgainAdaptor
import com.fps69.myapplication.R
import com.fps69.myapplication.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    lateinit var  binding:FragmentHistoryBinding
    private lateinit var buyAgainAdapter:BuyAgainAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHistoryBinding.inflate(layoutInflater,container,false)

        intRecyclerView()
        return binding.root
    }

    private fun intRecyclerView() {
            val menuFoodName= listOf("Classic Margherita Pizza",
                                        "Vegetarian Stir-Fry",
                                        "Chocolate Chip Cookies",
                                        "Chicken Alfredo Pasta",
                                        "Mango Salsa Chicken",
                                        "Quinoa Salad with Avocado",
                                        "Tomato Basil Bruschetta",
                                        "Beef and Broccoli Stir-Fry",
                                        "Caprese Salad",
                                        "Shrimp Scampi Pasta")
            val menuFoodPrice= listOf("67","65","63","31","90","54","76","34","21","65")

            val menuFoodImage= listOf(R.drawable.itemimage,
                                      R.drawable.itemimage,
                                      R.drawable.itemimage,
                                      R.drawable.itemimage,
                                      R.drawable.itemimage,
                                      R.drawable.itemimage,
                                      R.drawable.itemimage,
                                      R.drawable.itemimage,
                                      R.drawable.itemimage,
                                      R.drawable.itemimage)

        buyAgainAdapter= BuyAgainAdaptor(menuFoodName,menuFoodPrice,menuFoodImage)

        binding.apply {
            buyAgainRecyclerView.layoutManager=LinearLayoutManager(requireContext())
            buyAgainRecyclerView.adapter=buyAgainAdapter
        }
    }


}