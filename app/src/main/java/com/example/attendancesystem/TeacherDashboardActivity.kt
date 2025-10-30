package com.example.attendancesystem // Make sure this matches your package name

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attendancesystem.R
import com.example.attendancesystem.TeacherSubject
import com.example.attendancesystem.TeacherSubjectAdapter
import com.example.attendancesystem.SubjectDashboardActivity

class TeacherDashboardActivity : AppCompatActivity() {

    // Declare the views and adapter
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerViewSubjects: RecyclerView
    private lateinit var teacherSubjectAdapter: TeacherSubjectAdapter
    private lateinit var textViewWelcome: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure this layout exists and has the necessary views: activity_teacher_dashboard.xml
        setContentView(R.layout.activity_teacher_dashboard)

        // --- Get the Teacher's name from LoginActivity ---
        val teacherName = intent.getStringExtra("TEACHER_NAME") ?: "Teacher"

        // Find views
        toolbar = findViewById(R.id.toolbar)
        recyclerViewSubjects = findViewById(R.id.recyclerViewSubjects)
        textViewWelcome = findViewById(R.id.textViewWelcome)

        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "My Subjects"

        // Set the welcome text
        textViewWelcome.text = "Welcome, $teacherName!"

        // Setup the list
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        // --- This is placeholder data (My Subjects List) ---
        // TODO: This data will come from your database based on the logged-in user
        val subjectList = listOf(
            TeacherSubject("db_theory_s4", "Database Systems (Theory)", "Software Development & Machine Learning | Sem 4"),
            TeacherSubject("db_lab_s4", "Database Systems (Lab)", "Software Development & Machine Learning | Sem 4"),
            TeacherSubject("os_theory_s4", "Operating Systems", "Software Development & Machine Learning | Sem 4"),
            TeacherSubject("gees_intro_s2", "Intro to Electrical Systems", "Green Energy and Electrical Systems (GEES) | Sem 2")
        )

        // Initialize the adapter
        teacherSubjectAdapter = TeacherSubjectAdapter(subjectList) { clickedSubject ->
            // This code runs when a subject is clicked
            val intent = Intent(this, SubjectDashboardActivity::class.java)
            intent.putExtra("SUBJECT_ID", clickedSubject.subjectId)
            intent.putExtra("SUBJECT_NAME", clickedSubject.subjectName)
            startActivity(intent)
        }

        // Set the layout manager and adapter
        recyclerViewSubjects.layoutManager = LinearLayoutManager(this)
        recyclerViewSubjects.adapter = teacherSubjectAdapter
    }
}
