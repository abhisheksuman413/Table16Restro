package com.fps69.myapplication.Adaptar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fps69.myapplication.DetailsActivity
import com.fps69.myapplication.databinding.MenuItemBinding

class OriginalMenuAdapter(private val menuFoodNameOriginal: MutableList<String>,
                          private val menuFoodPriceOriginal: MutableList<String>,
                          private val menuFoodImageOriginal: MutableList<String>,
                          private val requireContex: Context
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


        init {

            binding.root.setOnClickListener {
                val position = adapterPosition
                if(position!= RecyclerView.NO_POSITION){
                    itemClickListnefr?.OnItemClickk(position)
                }
                val intent = Intent(requireContex, DetailsActivity::class.java)
                intent.putExtra("MenuitemName",menuFoodNameOriginal[position])
                intent.putExtra("menuImage",menuFoodImageOriginal[position])
                requireContex.startActivity(intent)
            }
        }

    }



    private val itemClickListnefr:OnItemClickListener?=null

    interface OnItemClickListener{

        fun OnItemClickk(position: Int){

        }

    }


}