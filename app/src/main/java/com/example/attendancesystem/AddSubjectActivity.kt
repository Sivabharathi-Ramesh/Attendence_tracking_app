package com.example.attendancesystem // Make sure this matches your package name

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.example.attendancesystem.R

class AddSubjectActivity : AppCompatActivity() {

    // Define all views
    private lateinit var toolbar: Toolbar
    private lateinit var editTextSubjectName: TextInputEditText
    private lateinit var editTextSubjectCode: TextInputEditText
    private lateinit var spinnerDepartment: AutoCompleteTextView
    private lateinit var spinnerYear: AutoCompleteTextView
    private lateinit var spinnerSemester: AutoCompleteTextView
    private lateinit var spinnerAssignTeacher: AutoCompleteTextView
    private lateinit var buttonSave: Button

    // Data lists for the dropdowns
    private val departments = arrayOf(
        "Applied Electronics & Chip Design (AECD)",
        "Green Energy and Electrical Systems (GEES)",
        "Software Development & Machine Learning (SDML)"
    ).sortedArray()
    private val years = arrayOf("First Year", "Second Year", "Third Year")
    private val semesters = arrayOf("1", "2", "3", "4", "5", "6")
    // Placeholder list of teachers
    private val teachers = arrayOf("Teacher A", "Teacher B", "Teacher C").sortedArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_subject) // Note: This assumes you created activity_add_subject.xml

        // Find all views (Assuming IDs match activity_add_subject.xml)
        toolbar = findViewById(R.id.toolbar)
        editTextSubjectName = findViewById(R.id.editTextSubjectName)
        editTextSubjectCode = findViewById(R.id.editTextSubjectCode) // Not mandatory per your request
        spinnerDepartment = findViewById(R.id.spinnerDepartment)
        spinnerYear = findViewById(R.id.spinnerYear)
        spinnerSemester = findViewById(R.id.spinnerSemester)
        spinnerAssignTeacher = findViewById(R.id.spinnerAssignTeacher)
        buttonSave = findViewById(R.id.buttonSave)

        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add New Subject"

        // Setup Dropdowns
        setupDropdowns()

        // Setup Save Button
        buttonSave.setOnClickListener {
            saveSubject()
        }
    }

    private fun setupDropdowns() {
        // Create adapters
        val departmentAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, departments)
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, years)
        val semesterAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, semesters)
        val teacherAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, teachers)

        // Set adapters
        spinnerDepartment.setAdapter(departmentAdapter)
        spinnerYear.setAdapter(yearAdapter)
        spinnerSemester.setAdapter(semesterAdapter)
        spinnerAssignTeacher.setAdapter(teacherAdapter)

        // Show dropdown immediately when tapped
        spinnerDepartment.setOnClickListener { spinnerDepartment.showDropDown() }
        spinnerYear.setOnClickListener { spinnerYear.showDropDown() }
        spinnerSemester.setOnClickListener { spinnerSemester.showDropDown() }
        spinnerAssignTeacher.setOnClickListener { spinnerAssignTeacher.showDropDown() }
    }

    private fun saveSubject() {
        val subjectName = editTextSubjectName.text.toString().trim()
        val subjectCode = editTextSubjectCode.text.toString().trim() // Not mandatory
        val department = spinnerDepartment.text.toString().trim()
        val teacher = spinnerAssignTeacher.text.toString().trim()

        // --- Validation ---
        if (subjectName.isEmpty()) {
            Toast.makeText(this, "Subject Name is required.", Toast.LENGTH_SHORT).show()
            return
        }
        if (department.isEmpty() || department !in departments) {
            Toast.makeText(this, "Please select a valid Department.", Toast.LENGTH_SHORT).show()
            return
        }
        if (teacher.isEmpty() || teacher !in teachers) {
            Toast.makeText(this, "Please assign a Teacher.", Toast.LENGTH_SHORT).show()
            return
        }

        // --- Placeholder Save ---
        // TODO: This is where Firebase/Database save logic will go

        Toast.makeText(this, "Subject '$subjectName' saved and assigned to $teacher!", Toast.LENGTH_LONG).show()
        finish() // Return to the Academic Management screen
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
