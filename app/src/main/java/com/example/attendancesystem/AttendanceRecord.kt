package com.example.attendancesystem

/**
 * Data class to hold the attendance information for a single student in the Roster.
 * Status: 1=Present, 2=Absent Informed (AI), 3=Absent Uninformed (AU), 0=Unmarked.
 */
data class AttendanceRecord(
    val studentId: String,
    val serialNo: Int,
    val rollNo: String,
    val studentName: String,
    var status: Int // This is a mutable variable to hold the selected attendance status
)
