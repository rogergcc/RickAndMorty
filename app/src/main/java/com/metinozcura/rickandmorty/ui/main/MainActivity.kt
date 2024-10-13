package com.metinozcura.rickandmorty.ui.main

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.metinozcura.rickandmorty.R
import com.metinozcura.rickandmorty.base.BaseActivity
import com.metinozcura.rickandmorty.databinding.ActivityMainBinding
import com.metinozcura.rickandmorty.ext.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    private val mainViewModel: MainViewModel by viewModels()
    private var currentNavController: LiveData<NavController>? = null

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getVM(): MainViewModel = mainViewModel

    override fun bindVM(binding: ActivityMainBinding, vm: MainViewModel) = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

        /*
        popBackStack is necessary in this case if the app is started from the deep link.
         */
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (currentNavController?.value?.popBackStack() != true) {
                    finish()
                }
            }
        })

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navGraphIds = listOf(
            R.navigation.graph_characters,
            R.navigation.graph_episodes,
            R.navigation.graph_locations
        )

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragmentContainerView,
            intent = intent
        )

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean =
        currentNavController?.value?.navigateUp() ?: false

    /**
     * Overriding popBackStack is necessary in this case if the app is started from the deep link.
     */
//    override fun onBackPressed() {
//        if (currentNavController?.value?.popBackStack() != true) {
//            super.onBackPressed()
//        }
//    }
}