package com.example.attendancesystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.example.attendancesystem.R

class AttendanceReportsActivity : AppCompatActivity() {

    // Define views
    private lateinit var toolbar: Toolbar
    private lateinit var spinnerDepartment: AutoCompleteTextView
    private lateinit var spinnerYear: AutoCompleteTextView
    private lateinit var spinnerSemester: AutoCompleteTextView
    private lateinit var spinnerSubject: AutoCompleteTextView
    private lateinit var btnViewSubjectAttendance: Button
    private lateinit var editTextSearchStudent: TextInputEditText
    private lateinit var btnViewStudentReport: Button

    // Data for dropdowns
    private val departments = arrayOf(
        "Applied Electronics & Chip Design (AECD)",
        "Green Energy and Electrical Systems (GEES)",
        "Software Development & Machine Learning (SDML)"
    )
    private val years = arrayOf("First Year", "Second Year", "Third Year")
    private val semesters = arrayOf("1", "2", "3", "4", "5", "6")

    // Placeholder data for subjects
    private val subjects = arrayOf("Database Systems (Theory)", "Database Systems (Lab)", "Data Structures")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure this layout exists: activity_attendance_reports.xml
        setContentView(R.layout.activity_attendance_reports)

        // Find views
        toolbar = findViewById(R.id.toolbar)
        spinnerDepartment = findViewById(R.id.spinnerDepartment)
        spinnerYear = findViewById(R.id.spinnerYear)
        spinnerSemester = findViewById(R.id.spinnerSemester)
        spinnerSubject = findViewById(R.id.spinnerSubject)
        btnViewSubjectAttendance = findViewById(R.id.btnViewSubjectAttendance)
        editTextSearchStudent = findViewById(R.id.editTextSearchStudent)
        btnViewStudentReport = findViewById(R.id.btnViewStudentReport)

        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Attendance Reports"

        // Populate all spinners
        setupSpinners()

        // Setup Button Clicks
        btnViewSubjectAttendance.setOnClickListener {
            viewSubjectReport()
        }

        btnViewStudentReport.setOnClickListener {
            viewStudentReport()
        }
    }

    private fun setupSpinners() {
        // Create adapters
        val departmentAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, departments)
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, years)
        val semesterAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, semesters)
        val subjectAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, subjects)

        // Set adapters
        spinnerDepartment.setAdapter(departmentAdapter)
        spinnerYear.setAdapter(yearAdapter)
        spinnerSemester.setAdapter(semesterAdapter)
        spinnerSubject.setAdapter(subjectAdapter)

        // Add click listener so dropdown shows immediately when tapped
        spinnerDepartment.setOnClickListener { spinnerDepartment.showDropDown() }
        spinnerYear.setOnClickListener { spinnerYear.showDropDown() }
        spinnerSemester.setOnClickListener { spinnerSemester.showDropDown() }
        spinnerSubject.setOnClickListener { spinnerSubject.showDropDown() }
    }

    private fun viewSubjectReport() {
        val selectedSubject = spinnerSubject.text.toString()
        if (selectedSubject.isNotEmpty()) {
            // Activate navigation to the matrix view
            val intent = Intent(this, SubjectAttendanceViewActivity::class.java)
            intent.putExtra("SUBJECT_NAME", selectedSubject)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please select a Subject first.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun viewStudentReport() {
        val studentQuery = editTextSearchStudent.text.toString().trim()
        if (studentQuery.isNotEmpty()) {
            // Activate navigation to the student report view
            val intent = Intent(this, StudentReportViewActivity::class.java)
            intent.putExtra("STUDENT_QUERY", studentQuery)
            startActivity(intent)
        } else {
            editTextSearchStudent.error = "Please enter a name or roll number to search."
        }
    }

    // Handle the back button click on the toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
