package com.fps69.myapplication.Adaptar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fps69.myapplication.Adaptar.MenuAdapter.MenuViewHolder
import com.fps69.myapplication.DataClass.MenuItems
import com.fps69.myapplication.DetailsActivity
import com.fps69.myapplication.RecipeDummyUserData
import com.fps69.myapplication.databinding.MenuItemBinding

class MenuAdapter(private val productList: List<MenuItems>, private val requireContex: Context) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val binding = MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class MenuViewHolder(private val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        // SetOn Click Lisner to open Menu Detais
        init {

            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    openDetailsActivity(position)
                }
            }
        }

        private fun openDetailsActivity(position: Int) {
            val menuItem=productList[position]
            // Intent to open details activity and pass data
            val inten = Intent(requireContex,DetailsActivity::class.java)
            inten.apply {
                putExtra("itemName",menuItem.foodName)
                putExtra("itemPrice",menuItem.foodPrice)
                putExtra("itemImage",menuItem.foodImag)
                putExtra("itemDec",menuItem.foodDec)
                putExtra("itemIng",menuItem.foodIngredient)
            }

            // Start Details Activity
            requireContex.startActivity(inten)
        }

        fun bind(position: Int) {
            binding.apply {
                menufoodname.text = productList[position].foodName
                menuitemprice.text = "$${productList[position].foodPrice}"

                //Using glide dependency kotlin for open image url
                Glide.with(binding.root).load("${productList[position].foodImag}").into(binding.menuImage)

            }

        }

    }
}

