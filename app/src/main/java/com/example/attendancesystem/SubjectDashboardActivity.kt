package com.example.attendancesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attendancesystem.R
import com.example.attendancesystem.EnrolledStudent
import com.example.attendancesystem.EnrolledStudentAdapter

class SubjectDashboardActivity : AppCompatActivity() {

    // Declare views
    private lateinit var toolbar: Toolbar
    private lateinit var textViewSubjectTitle: TextView
    private lateinit var btnTakeAttendance: Button
    private lateinit var btnViewPastAttendance: Button
    private lateinit var recyclerViewStudents: RecyclerView

    // Variables to hold data passed from the previous screen
    private lateinit var subjectId: String
    private lateinit var subjectName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure this layout exists: activity_subject_dashboard.xml
        setContentView(R.layout.activity_subject_dashboard)

        // 1. Get data passed from the Teacher Dashboard
        subjectId = intent.getStringExtra("SUBJECT_ID") ?: ""
        subjectName = intent.getStringExtra("SUBJECT_NAME") ?: "Subject Details"

        // 2. Find views from the layout
        toolbar = findViewById(R.id.toolbar)
        textViewSubjectTitle = findViewById(R.id.textViewSubjectTitle)
        btnTakeAttendance = findViewById(R.id.btnTakeAttendance)
        btnViewPastAttendance = findViewById(R.id.btnViewPastAttendance)
        recyclerViewStudents = findViewById(R.id.recyclerViewStudents)

        // 3. Setup Toolbar and Title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Subject Dashboard"

        // Display the actual subject name
        textViewSubjectTitle.text = subjectName

        // 4. Set Button Click Listeners (The Navigation Logic)
        btnTakeAttendance.setOnClickListener {
            // Open the custom Roster screen
            val intent = Intent(this, TakeAttendanceRosterActivity::class.java)
            intent.putExtra("SUBJECT_ID", subjectId)
            intent.putExtra("SUBJECT_NAME", subjectName)
            // No date is passed here, so the Roster defaults to today
            startActivity(intent)
        }

        btnViewPastAttendance.setOnClickListener {
            // Open the View/Edit Past Attendance screen (the calendar)
            val intent = Intent(this, ViewPastAttendanceActivity::class.java)
            intent.putExtra("SUBJECT_ID", subjectId)
            intent.putExtra("SUBJECT_NAME", subjectName)
            startActivity(intent)
        }

        // 5. Setup Student List (Using Placeholder Data)
        setupEnrolledStudentList()
    }

    private fun setupEnrolledStudentList() {
        // --- Placeholder Data for Enrolled Students ---
        // TODO: This data will come from your database based on the subjectId
        val studentList = listOf(
            EnrolledStudent(studentName = "Siva Bharathi", rollNumber = "24820007"),
            EnrolledStudent(studentName = "Aravindh", rollNumber = "24820001"),
            EnrolledStudent(studentName = "Gokul", rollNumber = "24820004"),
            EnrolledStudent(studentName = "Bavana", rollNumber = "24820003")
            // Add more students as needed for testing
        )

        // Initialize the adapter (use the simple constructor without click listener)
        val adapter = EnrolledStudentAdapter(studentList)

        // Set the layout manager and adapter for the RecyclerView
        recyclerViewStudents.layoutManager = LinearLayoutManager(this)
        recyclerViewStudents.adapter = adapter
    }

    // Handle the back button click on the toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
