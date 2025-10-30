package com.example.attendancesystem // Make sure this matches your package name

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attendancesystem.R

class StudentSubjectDetailActivity : AppCompatActivity() {

    // Declare views
    private lateinit var toolbar: Toolbar
    private lateinit var textViewSubjectTitle: TextView
    private lateinit var textViewPercentage: TextView
    private lateinit var textViewRatio: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerViewDetails: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_subject_detail)

        // 1. Get all data passed from the dashboard
        val subjectName = intent.getStringExtra("SUBJECT_NAME") ?: "Subject Detail"
        val attendedClasses = intent.getIntExtra("ATTENDED_CLASSES", 0)
        val totalClasses = intent.getIntExtra("TOTAL_CLASSES", 0)

        // Calculate percentage (handled safely)
        val percentage = if (totalClasses > 0) {
            (attendedClasses.toDouble() / totalClasses.toDouble()) * 100
        } else {
            0.0
        }

        // 2. Find all views from the layout
        toolbar = findViewById(R.id.toolbar)
        textViewSubjectTitle = findViewById(R.id.textViewSubjectTitle)
        textViewPercentage = findViewById(R.id.textViewPercentage)
        textViewRatio = findViewById(R.id.textViewRatio)
        progressBar = findViewById(R.id.progressBarAttendance)
        recyclerViewDetails = findViewById(R.id.recyclerViewDetails)

        // 3. Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Attendance Details" // Set static title

        // 4. Setup the Summary Card (UI elements)
        textViewSubjectTitle.text = subjectName
        textViewPercentage.text = "${percentage.toInt()}%"
        textViewRatio.text = "$attendedClasses / $totalClasses"
        progressBar.progress = percentage.toInt()

        // Set the color-coding for the progress bar based on 75% threshold
        val colorResource = if (percentage >= 75) {
            R.color.positive_green
        } else {
            R.color.negative_red
        }
        // Apply color filter
        progressBar.progressDrawable.setColorFilter(
            ContextCompat.getColor(this, colorResource),
            PorterDuff.Mode.SRC_IN
        )

        // 5. Setup the detailed list (Day-by-Day Log)
        setupDetailedList()
    }

    private fun setupDetailedList() {
        // --- Placeholder Data for the detailed list ---
        // TODO: This data will come from your database based on subjectId
        val detailList = listOf(
            StudentAttendanceDetail("Oct 24, 2025", "Present"),
            StudentAttendanceDetail("Oct 22, 2025", "Absent Uninformed"),
            StudentAttendanceDetail("Oct 21, 2025", "Present"),
            StudentAttendanceDetail("Oct 20, 2025", "Present"),
            StudentAttendanceDetail("Oct 19, 2025", "Absent Informed"),
            StudentAttendanceDetail("Oct 17, 2025", "Present")
        )
        // --- End of placeholder data ---

        // Initialize the adapter (StudentDetailAdapter must exist)
        val adapter = StudentDetailAdapter(detailList)
        recyclerViewDetails.layoutManager = LinearLayoutManager(this)
        recyclerViewDetails.adapter = adapter
    }

    // Handle the back button click on the toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
