package com.fps69.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.fps69.myapplication.Fragment.CongratsBottomSheetFragment
import com.fps69.myapplication.databinding.ActivityPayOutBinding

class PayOutActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPayOutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPayOutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.BackButtonPayOut.setOnClickListener {
            finish()
        }



        binding.placeMyOrderButton.setOnClickListener {
            val bottomSheetDialog = CongratsBottomSheetFragment()
            bottomSheetDialog.show(supportFragmentManager,"this")
        }
    }
}