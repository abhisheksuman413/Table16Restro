package com.fps69.myapplication.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fps69.myapplication.RecipeDummyUserData
import com.fps69.myapplication.databinding.CartItemBinding

class CartAdapter(private val productList: List<RecipeDummyUserData>):
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {


    private val ItemQuantities = IntArray(productList.size){1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return productList.size
    }


    inner class CartViewHolder(private val binding:CartItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            val quantitiesItem=ItemQuantities[position]
            binding.apply {
                cartfoodname.text=productList[position].name
                cartprice.text="$${productList[position].prepTimeMinutes.toString()}"
                quantity.text=quantitiesItem.toString()

                //Using glide dependency kotlin for open image url
                Glide.with(binding.root).load(productList[position].image).into(binding.cartimage)

                minusbutton.setOn
            }
        }


    }
}