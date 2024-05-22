package com.fps69.myapplication.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fps69.myapplication.RecipeDummyUserData
import com.fps69.myapplication.databinding.ItemlistpopularBinding

class PopularAdapter(private val productList: List<RecipeDummyUserData>) :
    RecyclerView.Adapter<PopularAdapter.PopulerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulerViewHolder {

        // Yha eak varible bna kr usko v return kr skte the
        return PopulerViewHolder(ItemlistpopularBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return productList.size

    }

    override fun onBindViewHolder(holder: PopulerViewHolder, position: Int) {
        val item = productList[position].name
        val image = productList[position].image
        val price = productList[position].prepTimeMinutes

        holder.bind(item,image,price)
    }

    class PopulerViewHolder(private val binding:ItemlistpopularBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: String, image: String, price:Int) {
            binding.fooditempopuler.text=item
            binding.pricepopuler.text="$${price.toString()}"

            //Using glide dependency kotlin for open image url
            Glide.with(binding.root).load(image).into(binding.populeritemimage)

        }

    }
}