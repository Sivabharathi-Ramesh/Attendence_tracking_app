package com.example.attendancesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.attendancesystem.R

class AdminDashboardActivity : AppCompatActivity() {

    // Declare views specific to Admin Dashboard
    private lateinit var toolbar: Toolbar
    private lateinit var textViewWelcome: TextView
    private lateinit var btnUserManagement: Button
    private lateinit var btnAcademicManagement: Button
    private lateinit var btnAttendanceReports: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure this layout exists: activity_admin_dashboard.xml
        setContentView(R.layout.activity_admin_dashboard)

        // --- Get the Admin's name from LoginActivity ---
        val adminName = intent.getStringExtra("ADMIN_NAME") ?: "Admin"

        // Find views (R.id.textViewWelcome must exist in your XML)
        toolbar = findViewById(R.id.toolbar)
        textViewWelcome = findViewById(R.id.textViewWelcome)
        btnUserManagement = findViewById(R.id.btnUserManagement)
        btnAcademicManagement = findViewById(R.id.btnAcademicManagement)
        btnAttendanceReports = findViewById(R.id.btnAttendanceReports)

        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Admin Dashboard"

        // Set the welcome text
        textViewWelcome.text = "Welcome, $adminName!"

        // Setup Button Click Listeners (Navigation Logic)

        // 1. User Management Button
        btnUserManagement.setOnClickListener {
            val intent = Intent(this, UserManagementActivity::class.java)
            startActivity(intent)
        }

        // 2. Academic Management Button
        btnAcademicManagement.setOnClickListener {
            val intent = Intent(this, AcademicManagementActivity::class.java)
            startActivity(intent)
        }

        // 3. Attendance Reports Button
        btnAttendanceReports.setOnClickListener {
            val intent = Intent(this, AttendanceReportsActivity::class.java)
            startActivity(intent)
        }
    }
}
