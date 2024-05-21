package com.fps69.myapplication.Adaptar

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fps69.myapplication.RecipeDummyUserData
import com.fps69.myapplication.databinding.CartItemBinding

class CartAdapter(private val productList: List<RecipeDummyUserData>):
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val mutableProductList = productList.toMutableList()


    private val ItemQuantities = IntArray(mutableProductList.size){1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return mutableProductList.size
    }


    inner class CartViewHolder(private val binding:CartItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            val quantitiesItem=ItemQuantities[position]
            binding.apply {
                cartfoodname.text=mutableProductList[position].name
                cartprice.text="$${mutableProductList[position].prepTimeMinutes.toString()}"
                quantity.text=quantitiesItem.toString()

                //Using glide dependency kotlin for open image url
                Glide.with(binding.root).load(mutableProductList[position].image).into(binding.cartimage)

                minusbutton.setOnClickListener {
                    decreaseQuantity(position)

                }
                plusbutton.setOnClickListener {
                    increaseQuantity(position)

                }
                deletebutton.setOnClickListener {
                    deleteItem(position)

                }
            }
        }
        private fun decreaseQuantity(position: Int){
            if(ItemQuantities[position]>1){
                ItemQuantities[position]--
                binding.quantity.text=ItemQuantities[position].toString()
            }
        }

        private fun increaseQuantity(position: Int){
            if(ItemQuantities[position]<9){
                ItemQuantities[position]++
                binding.quantity.text=ItemQuantities[position].toString()
            }
        }

        private fun deleteItem(position: Int){
            mutableProductList.removeAt(position)
            notifyItemRemoved(position) //notifyItemRemoved(position), the RecyclerView performs the necessary animations and updates to remove the item from the UI.
            notifyItemRangeChanged(position,mutableProductList.size)  /*  This method tells the adapter that a range
                                                                          of items starting from positionStart has changed.
                                                                          The RecyclerView will rebind and refresh all items
                                                                          in this range. This is particularly useful when the
                                                                          removal of an item affects the positions of subsequent
                                                                          items, which need to be updated.*/
        }
    }
}