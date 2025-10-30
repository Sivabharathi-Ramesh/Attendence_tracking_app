package com.example.attendancesystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attendancesystem.R

// Make sure these helper classes exist: StudentReportSubject and StudentReportAdapter
class StudentReportViewActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var textViewQuery: TextView
    private lateinit var textViewStudentName: TextView
    private lateinit var textViewRollNo: TextView
    private lateinit var textViewDeptYear: TextView
    private lateinit var recyclerViewSubjects: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Ensure this layout exists: activity_student_report_view.xml
        setContentView(R.layout.activity_student_report_view)

        // Get the search query passed from AttendanceReportsActivity
        val studentQuery = intent.getStringExtra("STUDENT_QUERY") ?: "No Query"

        // Find views
        toolbar = findViewById(R.id.toolbar)
        textViewQuery = findViewById(R.id.textViewQuery)
        textViewStudentName = findViewById(R.id.textViewStudentName)
        textViewRollNo = findViewById(R.id.textViewRollNo)
        textViewDeptYear = findViewById(R.id.textViewDeptYear)
        recyclerViewSubjects = findViewById(R.id.recyclerViewSubjects)

        // Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Student Report"

        // Display the query (for debugging/confirmation - set to View.GONE in final app)
        textViewQuery.text = "Search Query: $studentQuery"

        // --- TODO: Data Fetching Logic Here ---
        // Placeholder logic to simulate fetching a student's data based on the query.
        val studentId = "STD001"
        val studentName = "Siva Bharathi"
        val rollNo = "24820007"
        val deptYear = "SDML | 3rd Year | Sem 5"

        // Populate personal details
        textViewStudentName.text = studentName
        textViewRollNo.text = "Roll No: $rollNo"
        textViewDeptYear.text = deptYear

        // Setup the list of subjects
        setupSubjectReportList(studentId)
    }

    private fun setupSubjectReportList(studentId: String) {
        // --- Placeholder Data for Student Subjects/Attendance ---
        // This list simulates the data for the student's current semester.
        val subjectReportList = listOf(
            StudentReportSubject("OS501", "Operating Systems", 19, 20),
            StudentReportSubject("DB605", "Database Management", 14, 20),
            StudentReportSubject("DS302", "Data Structures", 16, 20),
            StudentReportSubject("GEE101", "Green Energy Intro", 10, 20)
        )

        // Initialize the adapter (StudentReportAdapter manages the list display)
        val adapter = StudentReportAdapter(subjectReportList) { subject ->
            // Tapping a subject can open the Individual Student Subject Detail view (Screen 11)
            Toast.makeText(this, "Opening detailed log for ${subject.subjectName}", Toast.LENGTH_SHORT).show()
            // val intent = Intent(this, StudentSubjectDetailActivity::class.java)
            // intent.putExtra("SUBJECT_ID", subject.subjectId)
            // intent.putExtra("STUDENT_ID", studentId)
            // startActivity(intent)
        }

        recyclerViewSubjects.layoutManager = LinearLayoutManager(this)
        recyclerViewSubjects.adapter = adapter
    }

    // Handle the back button click on the toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
