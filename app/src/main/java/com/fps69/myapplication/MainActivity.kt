package com.fps69.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fps69.myapplication.Fragment.Notification_Bottom_Fragment
import com.fps69.myapplication.databinding.ActivityChooseLocationBinding
import com.fps69.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController:NavController = findNavController(R.id.fragmentContainerView2)

        binding.bottomNavigationView.apply {
            setupWithNavController(navController)
        }

        binding.notificationImageButton.setOnClickListener {
            val bottomSheetDialog = Notification_Bottom_Fragment()
            bottomSheetDialog.show(supportFragmentManager,"Test")
        }


    }
}