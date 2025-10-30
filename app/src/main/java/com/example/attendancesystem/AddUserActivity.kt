package com.example.attendancesystem // Make sure this matches your package name

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.example.attendancesystem.R

class AddUserActivity : AppCompatActivity() {

    // Define all views
    private lateinit var toolbar: Toolbar
    private lateinit var spinnerRole: AutoCompleteTextView
    private lateinit var spinnerDepartment: AutoCompleteTextView
    private lateinit var spinnerYear: AutoCompleteTextView
    private lateinit var spinnerSemester: AutoCompleteTextView
    private lateinit var inputLayoutRollNo: TextInputLayout
    private lateinit var inputLayoutPassword: TextInputLayout
    private lateinit var inputLayoutDepartment: TextInputLayout
    private lateinit var inputLayoutYear: TextInputLayout
    private lateinit var inputLayoutSemester: TextInputLayout
    private lateinit var editTextName: TextInputEditText
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var buttonSave: Button

    // Data lists for the dropdowns
    private val roles = arrayOf("Admin", "Teacher", "Student")
    // Alphabetical order for professionalism
    private val departments = arrayOf(
        "Applied Electronics & Chip Design (AECD)",
        "Green Energy and Electrical Systems (GEES)",
        "Software Development & Machine Learning (SDML)"
    ).sortedArray()
    private val years = arrayOf("First Year", "Second Year", "Third Year")
    private val semesters = arrayOf("1", "2", "3", "4", "5", "6")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        // Find all views (Assuming IDs match activity_add_user.xml)
        toolbar = findViewById(R.id.toolbar)
        spinnerRole = findViewById(R.id.spinnerRole)
        spinnerDepartment = findViewById(R.id.spinnerDepartment)
        spinnerYear = findViewById(R.id.spinnerYear)
        spinnerSemester = findViewById(R.id.spinnerSemester)
        inputLayoutRollNo = findViewById(R.id.inputLayoutRollNo)
        inputLayoutPassword = findViewById(R.id.inputLayoutPassword)
        inputLayoutDepartment = findViewById(R.id.inputLayoutDepartment)
        inputLayoutYear = findViewById(R.id.inputLayoutYear)
        inputLayoutSemester = findViewById(R.id.inputLayoutSemester)
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        buttonSave = findViewById(R.id.buttonSave)

        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add New User"

        // Setup Dropdowns
        setupRoleDropdown()
        setupAcademicDropdowns()

        // Set initial visibility
        updateFormVisibility("Admin") // Default to the most restrictive view (Admin)

        // Setup Save Button
        buttonSave.setOnClickListener {
            saveUser()
        }
    }

    private fun setupRoleDropdown() {
        val roleAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, roles)
        spinnerRole.setAdapter(roleAdapter)

        // Show dropdown immediately when tapped
        spinnerRole.setOnClickListener { spinnerRole.showDropDown() }

        // Listener to change form based on role selection
        spinnerRole.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedRole = roles[position]
            updateFormVisibility(selectedRole)
        }
    }

    private fun setupAcademicDropdowns() {
        val departmentAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, departments)
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, years)
        val semesterAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, semesters)

        spinnerDepartment.setAdapter(departmentAdapter)
        spinnerYear.setAdapter(yearAdapter)
        spinnerSemester.setAdapter(semesterAdapter)

        // Show dropdown immediately when tapped
        spinnerDepartment.setOnClickListener { spinnerDepartment.showDropDown() }
        spinnerYear.setOnClickListener { spinnerYear.showDropDown() }
        spinnerSemester.setOnClickListener { spinnerSemester.showDropDown() }
    }

    private fun updateFormVisibility(role: String) {
        val isStudent = role == "Student"
        val needsDepartment = role != "Admin" // Admin does not need a department

        // Student Fields
        inputLayoutRollNo.visibility = if (isStudent) View.VISIBLE else View.GONE
        inputLayoutYear.visibility = if (isStudent) View.VISIBLE else View.GONE
        inputLayoutSemester.visibility = if (isStudent) View.VISIBLE else View.GONE

        // Admin/Teacher/Student Fields
        inputLayoutPassword.visibility = View.VISIBLE // All roles need a password
        inputLayoutDepartment.visibility = if (needsDepartment) View.VISIBLE else View.GONE // Show for Teacher/Student
    }

    private fun saveUser() {
        val role = spinnerRole.text.toString().trim()
        val name = editTextName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val password = inputLayoutPassword.editText?.text.toString().trim()
        val department = spinnerDepartment.text.toString().trim()
        val rollNo = inputLayoutRollNo.editText?.text.toString().trim()

        // --- Basic Validation ---
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Name, Email, and Password are required.", Toast.LENGTH_SHORT).show()
            return
        }

        if (role == "Student" && rollNo.isEmpty()) {
            Toast.makeText(this, "Roll Number is required for students.", Toast.LENGTH_SHORT).show()
            return
        }

        // --- Placeholder Save ---
        // TODO: This is where Firebase/Database save logic will go (Authentication and Firestore)

        Toast.makeText(this, "User '$name' (Role: $role) saved successfully!", Toast.LENGTH_LONG).show()
        finish() // Return to the User Management List
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
