package com.fps69.myapplication.Adaptar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fps69.myapplication.databinding.MenuItemBinding

class OriginalMenuAdapter(private val menuFoodNameOriginal: MutableList<String>,
                          private val menuFoodPriceOriginal: MutableList<String>,
                          private val menuFoodImageOriginal: MutableList<String>
):
    RecyclerView.Adapter<OriginalMenuAdapter.OriginalMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OriginalMenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OriginalMenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return menuFoodNameOriginal.size
    }

    override fun onBindViewHolder(holder: OriginalMenuViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class OriginalMenuViewHolder(private val binding: MenuItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.apply {
                menufoodname.text=menuFoodNameOriginal[position]
                menuitemprice.text="$${menuFoodPriceOriginal[position]}"

                //Using glide dependency kotlin for open image url
                Glide.with(binding.root).load(menuFoodImageOriginal[position]).into(binding.menuImage)

            }


        }

    }


}