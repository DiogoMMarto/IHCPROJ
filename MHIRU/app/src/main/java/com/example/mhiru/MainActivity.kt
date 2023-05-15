package com.example.mhiru


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.mhiru.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        var navView: NavigationView = binding.navView
        val navController = findNavController(R.id.fragment_container)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(

                R.id.nav_chats,R.id.nav_home,R.id.nav_form, R.id.nav_ai, R.id.nav_aboutus, R.id.nav_profile,
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val intent = intent
        if (intent.hasExtra("key")) {
            val myValue = intent.getStringExtra("key")


            if (myValue.equals("professional")) {
                navView.menu.clear()
                navView.inflateMenu(R.menu.activity_main_drawer_professional)

            }
            else if(myValue.equals("client")){
                navView.menu.clear()
                navView.inflateMenu(R.menu.activity_main_drawer_logoff)
            }
            else{
                navView.menu.clear()
                navView.inflateMenu(R.menu.activity_main_drawer_login)
            }
        }
        if (intent.hasExtra("fragmentToLoad")) {
            val myValue = intent.getStringExtra("fragmentToLoad")
            navController.navigate(R.id.nav_profile)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragment_container)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
