package com.example.pr3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.pr3.databinding.ActivityMainBinding
import fragments.*
import model.Word
import model.WordService

class MainActivity() : AppCompatActivity(), Navigation {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.fcvNavHost) as NavHost
        navController = navHost.navController

        setupActionBarWithNavController(navController)
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
        //b.bottomNav.setupWithNavController(navController)
    }

    override val wordService: WordService
        get() = (applicationContext as App).wordService

    override fun launchWordDetailedFragment(word: Word) {
        val action = MainFragmentDirections.actionMainFragmentToWordDetailedFragment(word)
        navController.navigate(action)
    }

    override fun launchAddWordFragment() {
        val action = MainFragmentDirections.actionMainFragmentToAddWordFragment()
        navController.navigate(action)
    }

    override fun onBackPressed() {
        val selectedItem = binding.bottomNav.selectedItemId
        if (selectedItem == R.id.mainFragment) {
            super.onBackPressed()
            return
        }
        binding.bottomNav.selectedItemId = R.id.mainFragment
        //supportFragmentManager.popBackStack(R.id.mainFragment, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        navController.popBackStack(R.id.mainFragment, false)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}