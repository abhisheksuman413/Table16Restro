package com.fps69.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fps69.myapplication.databinding.ActivityLoginBinding
import com.fps69.myapplication.databinding.ActivitySignBinding

class SignActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Tvalreadyhaveaccount.setOnClickListener {
            val intent = Intent(this@SignActivity, LoginActivity::class.java)
            startActivity(intent)
        }

    }
}