package com.example.attendancesystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.attendancesystem.R

class SubjectAttendanceViewActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var textViewSubjectName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure this layout exists: activity_subject_attendance_view.xml
        setContentView(R.layout.activity_subject_attendance_view)

        val subjectName = intent.getStringExtra("SUBJECT_NAME") ?: "Attendance Matrix"

        toolbar = findViewById(R.id.toolbar)
        textViewSubjectName = findViewById(R.id.textViewSubjectName)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Attendance Matrix"

        textViewSubjectName.text = "Matrix View for: $subjectName"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
