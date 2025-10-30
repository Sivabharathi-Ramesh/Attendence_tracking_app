package com.example.attendancesystem // Make sure this matches your package name

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView // <-- Handles the modern dropdown functionality
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.attendancesystem.R

class LoginActivity : AppCompatActivity() {

    // Declare the AutoCompleteTextView as a property
    private lateinit var autoCompleteRole: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Find all the views from your activity_login.xml
        val editTextEmail = findViewById<EditText>(R.id.editTextEmail)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        // Find the new AutoCompleteTextView
        autoCompleteRole = findViewById(R.id.autoCompleteRole)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)

        // Define the roles for the dropdown
        val roles = arrayOf("Admin", "Teacher", "Student")

        // Create an ArrayAdapter using the roles array and the simple dropdown layout
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, roles)

        // Apply the adapter to the AutoCompleteTextView
        autoCompleteRole.setAdapter(adapter)

        // Add a click listener so the dropdown shows immediately when tapped
        autoCompleteRole.setOnClickListener {
            autoCompleteRole.showDropDown()
        }

        // Set the listener for the Login button
        buttonLogin.setOnClickListener {
            // Get the text from the AutoCompleteTextView
            val selectedRole = autoCompleteRole.text.toString().trim()
            val email = editTextEmail.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            // Basic validation
            if (email.isEmpty() || password.isEmpty() || selectedRole.isEmpty()) {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // --- NAVIGATION LOGIC ---
            // Determine placeholder name to pass to the dashboard
            val userName = if (selectedRole == "Student") "Siva Bharathi" else "$selectedRole User"

            when (selectedRole) {
                "Admin" -> {
                    val intent = Intent(this, AdminDashboardActivity::class.java).apply {
                        putExtra("ADMIN_NAME", userName)
                    }
                    startActivity(intent)
                }
                "Teacher" -> {
                    val intent = Intent(this, TeacherDashboardActivity::class.java).apply {
                        putExtra("TEACHER_NAME", userName)
                    }
                    startActivity(intent)
                }
                "Student" -> {
                    val intent = Intent(this, StudentDashboardActivity::class.java).apply {
                        putExtra("STUDENT_NAME", userName)
                    }
                    startActivity(intent)
                }
            }
        }
    }
}
