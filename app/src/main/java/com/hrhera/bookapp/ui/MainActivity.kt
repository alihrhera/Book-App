package com.hrhera.bookapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hrhera.bookapp.R
import com.hrhera.bookapp.databinding.ActivityMainBinding
import com.hrhera.bookapp.ui.fragment.home.HomeViewModel
import com.hrhera.bookapp.ui.fragment.profile.ProfileViewModel
import com.hrhera.bookapp.util.Status

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val homeViewModel: HomeViewModel by viewModels()
    val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Status.init(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navController.navigate(R.id.splashFragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home_graph,
                R.id.navigation_profile_graph,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        object : Thread() {
            override fun run() {
                super.run()
                try {
                    runOnUiThread {
                        supportActionBar?.hide()
                        navView.visibility = View.GONE
                    }
                    sleep(3000)
                    runOnUiThread {
                        navView.visibility = View.VISIBLE
                        supportActionBar?.show()
                        navController.navigate(R.id.navigation_home_graph)

                    }
                } catch (e: Exception) {
                }
            }
        }.start()


    }
}