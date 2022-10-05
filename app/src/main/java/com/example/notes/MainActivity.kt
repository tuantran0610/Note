package com.example.notes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notes.ui.fragments.HomeFragment
import com.example.notes.ui.fragments.PieChartFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        navController = findNavController(R.id.fragmentContainerView)
//        setupActionBarWithNavController(navController)
        val homeFragment = HomeFragment()
        val pieChartFragment = PieChartFragment()

        val bottomNavigationView =findViewById<BottomNavigationView>(R.id.buttonNavigation)
        setThatFragment(homeFragment)
        Log.d("LOG", "here")
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> setThatFragment(homeFragment)
                R.id.pie_chart -> setThatFragment(pieChartFragment)

            }
            true
        }


    }
    private fun setThatFragment(fragment: Fragment) {
        if (fragment != null){

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentContainerView, fragment)
            transaction.commit()
        }
    }



}