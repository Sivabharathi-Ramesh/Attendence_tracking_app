package com.example.attendancesystem // Make sure this matches your package name

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attendancesystem.R
import com.example.attendancesystem.StudentSubject // <-- Changed from TeacherSubject
import com.example.attendancesystem.StudentSubjectAdapter // <-- Changed from TeacherSubjectAdapter
import com.example.attendancesystem.StudentSubjectDetailActivity // <-- Changed navigation target

class StudentDashboardActivity : AppCompatActivity() { // Renamed class

    // Declare the views and adapter
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerViewSubjects: RecyclerView
    private lateinit var studentSubjectAdapter: StudentSubjectAdapter // Changed adapter type
    private lateinit var textViewWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure this layout exists and has the necessary views: activity_student_dashboard.xml
        setContentView(R.layout.activity_student_dashboard)

        // --- Get the Student's name from LoginActivity ---
        val studentName = intent.getStringExtra("STUDENT_NAME") ?: "Student"

        // Find views
        toolbar = findViewById(R.id.toolbar)
        recyclerViewSubjects = findViewById(R.id.recyclerViewSubjects)
        textViewWelcome = findViewById(R.id.textViewWelcome)

        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "My Attendance" // Changed title

        // Set the welcome text
        textViewWelcome.text = "Welcome, $studentName!"

        // Setup the list
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // --- This is placeholder data (My Subjects List) ---
        // TODO: This data will come from your database based on the logged-in user
        val subjectList = listOf(
            // Data fields match the StudentSubject Data Class (id, name, attended, total)
            StudentSubject("db_theory_s4", "Database Systems (Theory)", 17, 20),
            StudentSubject("os_theory_s4", "Operating Systems", 18, 20),
            StudentSubject("db_lab_s4", "Database Systems (Lab)", 8, 8),
            StudentSubject("ds_theory_s3", "Data Structures", 15, 16)
        )

        // Initialize the adapter
        studentSubjectAdapter = StudentSubjectAdapter(subjectList) { clickedSubject ->
            // This code runs when a subject is clicked
            val intent = Intent(this, StudentSubjectDetailActivity::class.java) // Changed target
            // Pass necessary data to the detail screen
            intent.putExtra("SUBJECT_NAME", clickedSubject.subjectName)
            intent.putExtra("ATTENDED_CLASSES", clickedSubject.attendedClasses)
            intent.putExtra("TOTAL_CLASSES", clickedSubject.totalClasses)
            startActivity(intent)
        }

        // Set the layout manager and adapter
        recyclerViewSubjects.layoutManager = LinearLayoutManager(this)
        recyclerViewSubjects.adapter = studentSubjectAdapter
    }
}
