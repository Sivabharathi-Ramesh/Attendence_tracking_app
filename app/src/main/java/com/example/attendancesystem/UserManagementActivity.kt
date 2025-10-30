package com.example.attendancesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.attendancesystem.R

class UserManagementActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var fabAddUser: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_management)

        // Find all the views from the XML
        toolbar = findViewById(R.id.toolbar)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        fabAddUser = findViewById(R.id.fabAddUser)

        // Setup the Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "User Management"

        // Initialize the Adapter
        val adapter = UserManagementViewPagerAdapter(this) // This adapter must exist

        // Set the adapter on the ViewPager
        viewPager.adapter = adapter

        // Link the TabLayout to the ViewPager (to show tab titles)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // Set the text for each tab
            when (position) {
                0 -> tab.text = "Teachers"
                1 -> tab.text = "Students"
            }
        }.attach()

        // Set the click listener for the "+" button
        fabAddUser.setOnClickListener {
            // Create an Intent to open AddUserActivity
            val intent = Intent(this, AddUserActivity::class.java)
            // Start the new activity
            startActivity(intent)
        }
    }

    // Handle the back button click on the toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
