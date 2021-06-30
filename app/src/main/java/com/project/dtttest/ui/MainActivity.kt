package com.project.dtttest.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.project.dtttest.R
import com.project.dtttest.databinding.ActivityMainBinding
import com.project.dtttest.repository.Repository

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        //Hide Bottom Navigation View in HouseDetailFragment
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.houseDetailFragment -> binding.bottomNavView.visibility = View.GONE
                R.id.overviewFragment -> binding.bottomNavView.visibility = View.VISIBLE
                R.id.informationFragment -> binding.bottomNavView.visibility = View.VISIBLE
            }
        }

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        binding.bottomNavView.setupWithNavController(navHostFragment.findNavController())
    }
}