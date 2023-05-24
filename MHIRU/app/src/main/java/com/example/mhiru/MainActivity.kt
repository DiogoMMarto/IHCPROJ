package com.example.mhiru


import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.mhiru.databinding.ActivityMainBinding
import com.example.mhiru.ui.conversation.ConversationFragment

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
                R.id.nav_form, R.id.nav_ai, R.id.nav_aboutus, R.id.nav_profile,R.id.nav_clients, R.id.nav_chats, R.id.nav_settings, R.id.nav_conversations
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
                navController.navigate(R.id.nav_clients)
                binding.appBarMain.profilebtn.visibility= View.VISIBLE
                GlobalVariables.user="professional"

            }
            else if(myValue.equals("client")){
                navView.menu.clear()
                navView.inflateMenu(R.menu.activity_main_drawer_logoff)
                binding.appBarMain.profilebtn.visibility= View.VISIBLE
                GlobalVariables.user="client"
            }
            else if(myValue.equals("guest")){
                navView.menu.clear()
                navView.inflateMenu(R.menu.activity_main_drawer_login)
                GlobalVariables.user="guest"

            }
        }
        if (intent.hasExtra("fragmentToLoad")) {
            var myValue = intent.getStringExtra("fragmentToLoad")
            if (myValue.equals("conversation")) {
                ConversationFragment()
                navController.navigate(R.id.nav_conversation)
            } else {
                navController.navigate(R.id.nav_profile)
            }
        }
        binding.appBarMain.profilebtn.setOnClickListener {
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
