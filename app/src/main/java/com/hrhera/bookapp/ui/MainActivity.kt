package com.hrhera.bookapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hrhera.bookapp.R
import com.hrhera.bookapp.data.models.User
import com.hrhera.bookapp.databinding.ActivityMainBinding
import com.hrhera.bookapp.ui.fragment.category.CategoryViewModel
import com.hrhera.bookapp.ui.fragment.home.FavoriteViewModel
import com.hrhera.bookapp.ui.fragment.home.HomeViewModel
import com.hrhera.bookapp.ui.fragment.login.LoginViewModel
import com.hrhera.bookapp.ui.fragment.profile.ProfileViewModel
import com.hrhera.bookapp.util.Status
import kotlinx.coroutines.*

import com.hrhera.bookapp.ui.fragment.show_book.BookViewModel
import com.hrhera.bookapp.ui.fragment.signup.SignUpViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val homeViewModel: HomeViewModel by viewModels()
    val profileViewModel: ProfileViewModel by viewModels()
    val favoriteViewModel: FavoriteViewModel by viewModels()
    val categoryViewModel: CategoryViewModel by viewModels()
    val loginViewModel: LoginViewModel by viewModels()
    val signUpViewModel: SignUpViewModel by viewModels()
    val bookViewModel: BookViewModel by viewModels()

    lateinit var navController: NavController
    var navView: BottomNavigationView? = null
    private val bottomNav get() = navView!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Status.init(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        navView = binding.navView
        bottomNav.visibility = View.GONE
        navController = findNavController(R.id.nav_host_fragment_activity_main)

        navController.navigate(R.id.splashFragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.navigation_profile,
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNav.setupWithNavController(navController)

        GlobalScope.launch {
            switchFromSplashToLogin() }


    }


    private suspend fun switchFromSplashToLogin() {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                bottomNav.visibility = View.GONE
            }
            delay(3000)
            withContext(Dispatchers.Main) {
                navController.navigate(R.id.loginFragment)
            }
        }
    }

    fun afterLoginInit(user: User) {
        homeViewModel.getSliderData()
        homeViewModel.getPopularData()
        homeViewModel.getRecommendedData()
        profileViewModel.setUser(user)
    }


}