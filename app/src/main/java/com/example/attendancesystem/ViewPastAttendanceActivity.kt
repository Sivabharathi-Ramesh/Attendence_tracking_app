package com.example.attendancesystem // Make sure this matches your package name

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.attendancesystem.R
import java.util.*

class ViewPastAttendanceActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var calendarView: CalendarView

    private lateinit var subjectId: String
    private lateinit var subjectName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_past_attendance)

        // 1. Get subject data passed from the Subject Dashboard
        subjectId = intent.getStringExtra("SUBJECT_ID") ?: ""
        subjectName = intent.getStringExtra("SUBJECT_NAME") ?: "Past Attendance"

        // 2. Find views
        toolbar = findViewById(R.id.toolbar)
        calendarView = findViewById(R.id.calendarView)

        // 3. Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Past Attendance: $subjectName" // Set dynamic title

        // 4. Configure Calendar
        // Prevent selecting a future date
        calendarView.maxDate = System.currentTimeMillis()

        // 5. Set Listener for Date Selection
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->

            // Convert selected date (year, month, day) into milliseconds to pass easily
            val selectedCalendar = Calendar.getInstance()
            // Note: Month in CalendarView is 0-indexed (Jan=0, Dec=11)
            selectedCalendar.set(year, month, dayOfMonth)
            val selectedDateInMillis = selectedCalendar.timeInMillis

            // Navigate to the Roster screen, passing the date
            val intent = Intent(this, TakeAttendanceRosterActivity::class.java).apply {
                putExtra("SUBJECT_ID", subjectId)
                putExtra("SUBJECT_NAME", subjectName)
                putExtra("SELECTED_DATE", selectedDateInMillis) // <-- Critical: Passing the selected date
            }
            startActivity(intent)

            Toast.makeText(this, "Loading roster for $dayOfMonth/${month + 1}/$year...", Toast.LENGTH_SHORT).show()
        }
    }

    // Handle the back button click on the toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
