package com.fps69.myapplication.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.fps69.myapplication.databinding.BuyAgainItemBinding

class BuyAgainAdaptor (private val buyFoodName:List<String>,
                       private val buyFoodPrice:List<String>,
                       private val buyFoodImage:List<Int>):
    RecyclerView.Adapter<BuyAgainAdaptor.BuyAgainViewHolder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BuyAgainAdaptor.BuyAgainViewHolder {
        val binding = BuyAgainItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BuyAgainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BuyAgainAdaptor.BuyAgainViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
       return buyFoodName.size
    }


    inner class BuyAgainViewHolder(private val binding:BuyAgainItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                buyAgainFoodName.text=buyFoodName[position].toString()
                buyAgainFoodPrice.text="$${buyFoodPrice[position].toString()}"
                buyAgainFoodImage.setImageResource(buyFoodImage[position].toInt())
            }
        }

    }
}