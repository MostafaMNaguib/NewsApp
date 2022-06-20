package com.mostafamnaguib.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.mostafamnaguib.newsapp.R
import com.mostafamnaguib.newsapp.databinding.ActivityMainBinding
import com.mostafamnaguib.newsapp.ui.vms.ArticlesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private val tAG = MainActivity::class.java.simpleName
    val viewModel: ArticlesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        navController = navHostFragment?.findNavController()!!
        navController.setGraph(R.navigation.nav_graph)

    }
}