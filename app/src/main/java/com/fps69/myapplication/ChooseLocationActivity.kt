package com.fps69.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.fps69.myapplication.databinding.ActivityChooseLocationBinding

class ChooseLocationActivity : AppCompatActivity() {

    lateinit var binding : ActivityChooseLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityChooseLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val locationList= arrayOf("jaipur","Aurangabad","Patna","Sikar")

        val adaptorForList = ArrayAdapter(this@ChooseLocationActivity,android.R.layout.simple_list_item_1,locationList)
        val autoCompleteTextView = binding.ListOflocation
        autoCompleteTextView.setAdapter(adaptorForList)


        // Iss code ko hta dena hai
        binding.textView19.setOnClickListener {
            val intent = Intent(this@ChooseLocationActivity,MainActivity::class.java)
            startActivity(intent)

        }
    }
}