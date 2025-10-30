package com.example.attendancesystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.attendancesystem.R

class AttendanceRosterAdapter(
    private val attendanceRecords: List<AttendanceRecord>
) : RecyclerView.Adapter<AttendanceRosterAdapter.RosterViewHolder>() {

    /**
     * The ViewHolder class.
     * This holds the view references for a single row (item_attendance_roster.xml).
     */
    class RosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Views for student info
        private val serialAndRollNo: TextView = itemView.findViewById(R.id.textViewSerialAndRollNo)
        private val studentNameTextView: TextView = itemView.findViewById(R.id.textViewStudentName)

        // Views for attendance selection
        private val radioGroup: RadioGroup = itemView.findViewById(R.id.radioGroupAttendance)
        private val radioPresent: RadioButton = itemView.findViewById(R.id.radioPresent)
        private val radioAbsentInformed: RadioButton = itemView.findViewById(R.id.radioAbsentInformed)
        private val radioAbsentUninformed: RadioButton = itemView.findViewById(R.id.radioAbsentUninformed)

        fun bind(record: AttendanceRecord) {
            // Display student information
            serialAndRollNo.text = "${record.serialNo}. (${record.rollNo})"
            studentNameTextView.text = record.studentName

            // Set the checked state based on the record's current status
            when (record.status) {
                1 -> radioPresent.isChecked = true          // Present
                2 -> radioAbsentInformed.isChecked = true   // Absent Informed
                3 -> radioAbsentUninformed.isChecked = true // Absent Uninformed
                else -> radioGroup.clearCheck()             // Unmarked (0)
            }

            // Set listener to update the record's status whenever a radio button is clicked
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                val newStatus = when (checkedId) {
                    R.id.radioPresent -> 1
                    R.id.radioAbsentInformed -> 2
                    R.id.radioAbsentUninformed -> 3
                    else -> 0
                }
                // Update the mutable status property in the data class
                record.status = newStatus
            }
        }
    }

    /**
     * Creates the ViewHolder by inflating the single row layout.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RosterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_attendance_roster, parent, false)
        return RosterViewHolder(view)
    }

    /**
     * Binds the data from the list to the ViewHolder.
     */
    override fun onBindViewHolder(holder: RosterViewHolder, position: Int) {
        holder.bind(attendanceRecords[position])
    }

    /**
     * Returns the total number of items.
     */
    override fun getItemCount(): Int {
        return attendanceRecords.size
    }

    /**
     * Public method to retrieve the final attendance data for saving.
     */
    fun getAttendanceData(): List<AttendanceRecord> {
        return attendanceRecords
    }
}
