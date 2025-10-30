package com.example.attendancesystem // Make sure this matches your package name

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.attendancesystem.R
import java.text.SimpleDateFormat
import java.util.*

class TakeAttendanceRosterActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerViewRoster: RecyclerView
    private lateinit var buttonSelectDate: Button
    private lateinit var buttonSaveAttendance: Button
    private lateinit var rosterAdapter: AttendanceRosterAdapter
    private lateinit var textViewSubjectName: TextView

    // Use Calendar for date management and SimpleDateFormat for display
    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd - MM - yyyy", Locale.US)

    private lateinit var subjectId: String
    private lateinit var subjectName: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_attendance_roster)

        // 1. Get data passed from the Subject Dashboard
        subjectId = intent.getStringExtra("SUBJECT_ID") ?: ""
        subjectName = intent.getStringExtra("SUBJECT_NAME") ?: "Take Attendance"

        // Check if a specific date was passed from the calendar screen
        val selectedDateInMillis = intent.getLongExtra("SELECTED_DATE", -1L)

        if (selectedDateInMillis != -1L) {
            // A date was passed, set the calendar to it
            calendar.timeInMillis = selectedDateInMillis
        }
        // If no date was passed, 'calendar' automatically defaults to today.


        // 2. Find views
        toolbar = findViewById(R.id.toolbar)
        recyclerViewRoster = findViewById(R.id.recyclerViewRoster)
        buttonSelectDate = findViewById(R.id.buttonSelectDate)
        buttonSaveAttendance = findViewById(R.id.buttonSaveAttendance)
        textViewSubjectName = findViewById(R.id.textViewSubjectName)


        // 3. Setup Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Mark Attendance"

        // 4. Setup Subject Name Display
        textViewSubjectName.text = "Subject: $subjectName"

        // 5. Setup Date Picker and initial date display
        // This will now show EITHER today's date or the selected date
        updateDateButtonText(calendar.timeInMillis)
        buttonSelectDate.setOnClickListener { showDatePickerDialog() }

        // 6. Setup the Roster List
        setupRosterList()

        // 7. Setup Save Button Click
        buttonSaveAttendance.setOnClickListener {
            saveAttendance()
        }
    }

    private fun setupRosterList() {
        // --- Placeholder Data for the Roster ---
        // TODO: This data should be loaded from the database based on the subjectId and date.
        val dummyStudents = mutableListOf<AttendanceRecord>()
        val studentNames = listOf("Aravindh", "Aswin", "Bavana", "Gokul", "Hariharan", "Meenatchi", "Siva Bharathi", "Visal Stephen Raj")
        val rollNoPrefix = 24820000

        studentNames.forEachIndexed { index, name ->
            dummyStudents.add(
                AttendanceRecord(
                    studentId = "SUD00${index + 1}",
                    serialNo = index + 1,
                    rollNo = (rollNoPrefix + index + 1).toString(),
                    studentName = name,
                    // Default all students to Present (1) when the roster loads, as is common practice
                    status = 1
                )
            )
        }

        rosterAdapter = AttendanceRosterAdapter(dummyStudents)
        recyclerViewRoster.layoutManager = LinearLayoutManager(this)
        recyclerViewRoster.adapter = rosterAdapter
    }

    private fun showDatePickerDialog() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val picker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            calendar.set(selectedYear, selectedMonth, selectedDay)
            updateDateButtonText(calendar.timeInMillis)
            // TODO: Later: Load attendance data for the newly selected date
        }, year, month, day)

        // Prevent selecting a future date
        picker.datePicker.maxDate = System.currentTimeMillis()
        picker.show()
    }

    private fun updateDateButtonText(time: Long) {
        buttonSelectDate.text = dateFormat.format(Date(time))
    }

    private fun saveAttendance() {
        val attendanceData = rosterAdapter.getAttendanceData()
        val date = buttonSelectDate.text.toString()

        val unmarkedCount = attendanceData.count { it.status == 0 }

        if (unmarkedCount > 0) {
            Toast.makeText(this, "$unmarkedCount students not marked. Please mark all attendance.", Toast.LENGTH_LONG).show()
            return
        }

        // --- TODO: Send final attendanceData to Database here ---

        Toast.makeText(this, "Attendance for $date saved successfully for $subjectName!", Toast.LENGTH_SHORT).show()
        finish() // Close the activity after saving
    }

    // Handle the back button click on the toolbar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
