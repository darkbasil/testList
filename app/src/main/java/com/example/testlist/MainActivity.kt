package com.example.testlist

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.testlist.databinding.ActivityMainBinding
import com.example.testlist.utils.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        repository = ViewModelProvider(this).get(Repository::class.java)
        repository.fillItems(this.applicationContext)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController

        binding.buttonToFront.setOnClickListener {
            if (binding.buttonToFront.isChecked) {
                binding.buttonToBack.isChecked = false
                navController.navigate(R.id.action_BackFragment_to_FrontFragment)
            }
            else {
                binding.buttonToFront.isChecked = true
            }
        }

        binding.buttonToBack.setOnClickListener {
            if (binding.buttonToBack.isChecked) {
                binding.buttonToFront.isChecked = false
                navController.navigate(R.id.actionFrontFragment_to_BackFragment)
            }
            else {
                binding.buttonToBack.isChecked = true
            }
        }
    }
}