package com.fps69.myapplication.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fps69.myapplication.Adaptar.MenuAdapter.MenuViewHolder
import com.fps69.myapplication.RecipeDummyUserData
import com.fps69.myapplication.databinding.MenuItemBinding

class MenuAdapter(private val productList: List<RecipeDummyUserData>):
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class MenuViewHolder(private val binding:MenuItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                menufoodname.text=productList[position].name
                menuitemprice.text="$${productList[position].prepTimeMinutes}"

                //Using glide dependency kotlin for open image url
                Glide.with(binding.root).load(productList[position].image).into(binding.menuImage)
            }

        }

    }

}