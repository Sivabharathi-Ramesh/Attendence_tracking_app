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

class AcademicManagementActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var fabAddAcademic: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure this layout exists: activity_academic_management.xml
        setContentView(R.layout.activity_academic_management)

        // Find all the views from the XML
        toolbar = findViewById(R.id.toolbar)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        fabAddAcademic = findViewById(R.id.fabAddAcademic)

        // Setup the Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Academic Management"

        // Initialize the Adapter (AcademicManagementViewPagerAdapter must exist)
        val adapter = AcademicManagementViewPagerAdapter(this)

        // Set the adapter on the ViewPager
        viewPager.adapter = adapter

        // Link the TabLayout to the ViewPager (to show tab titles)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // Set the text for each tab based on the Admin's plan
            when (position) {
                0 -> tab.text = "Departments"
                1 -> tab.text = "Subjects"
            }
        }.attach()

        // Set the click listener for the "+" button to open AddSubjectActivity
        fabAddAcademic.setOnClickListener {
            val intent = Intent(this, AddSubjectActivity::class.java)
            startActivity(intent)
        }
    }

    // Handle the back button click on the toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
