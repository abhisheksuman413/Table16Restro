package com.fps69.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.fps69.myapplication.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foodName = intent.getStringExtra("itemName")
        val foodIamge = intent.getStringExtra("itemImage")
        val description = intent.getStringExtra("itemDec")
        val ingredients = intent.getStringExtra("itemIng")

        binding.detailFoodname.text=foodName
        binding.DescriptionTV.text=description
        binding.IngredientsTV.text=ingredients
        Glide.with(binding.root).load(foodIamge).into(binding.detailsFoodImage)



        binding.backButtonDetails.setOnClickListener {
            finish()
        }
    }
}