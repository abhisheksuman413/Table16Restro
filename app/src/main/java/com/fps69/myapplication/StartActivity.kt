package com.fps69.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fps69.myapplication.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
     lateinit var binding:ActivityStartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Nextbutton.setOnClickListener {
            val intent = Intent(this@StartActivity,LoginActivity::class.java)
            startActivity(intent)

        }
    }
}